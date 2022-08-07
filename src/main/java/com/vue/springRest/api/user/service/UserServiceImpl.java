package com.vue.springRest.api.user.service;


import com.vue.springRest.api.auth.model.Authority;
import com.vue.springRest.api.dto.UserDTO;
import com.vue.springRest.api.user.mapper.UserMapper;
import com.vue.springRest.api.user.model.UserModel;
import com.vue.springRest.common.util.SecurityUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public List<UserModel> getAllUser() {
        // 모든 유저리스트 가져오기
        List<UserModel> allUserList = userMapper.getAllUser();
        //


        // 유저들의 권한정보를 가져와서 유저권한에 넣어줌
        allUserList.forEach(userModel -> userModel.setAuthorities(userMapper.findOneAuthoritiesByUsername(userModel.getUser_id())));
        allUserList.forEach(userModel -> {
            assert userModel.getAuthorities() != null;
            Set<Authority> auth = userModel.getAuthorities();


            Iterator<Authority> iterSet = auth.iterator();
            StringBuilder strAuth = new StringBuilder();
            while (iterSet.hasNext()) {//값이 있으면 true 없으면 false
                strAuth.append(iterSet.next().getAuthority_name());
                strAuth.append(", ");
            }

            userModel.setAuth(strAuth.toString());
        });
        return allUserList;
    }

    @Transactional
    public UserModel signup(UserDTO userDTO) {
        if (this.findOneWithAuthoritiesByUsername(userDTO.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저 입니다.");
        }

        Authority authority = Authority.builder()
                .authority_name("ROLE_USER")
                .build(); // 회원가입이 가능할시 Authority entity 의 authorityName 을 ROLE_USER 로 세팅

        UserModel userModel = UserModel.builder()
                .user_id(userDTO.getUsername())
                .user_pw(passwordEncoder.encode(userDTO.getPassword()))
                .user_name(userDTO.getUser_name())
                .user_email(userDTO.getUser_email())
                .user_address(userDTO.getUser_address())
                .user_phone(userDTO.getUser_phone())
                .authorities(Collections.singleton(authority))
                .user_activated(true)
                .build();

        this.saveUser(userModel);

        return userModel;
    }

    @Transactional
    public UserModel modifyUserInfo(UserDTO userDTO) {
        UserModel userModel = UserModel.builder()
                .user_id(userDTO.getUsername())
                .user_pw(passwordEncoder.encode(userDTO.getPassword()))
                .user_name(userDTO.getUser_name())
                .user_email(userDTO.getUser_email())
                .user_address(userDTO.getUser_address())
                .user_phone(userDTO.getUser_phone())
                .build();

        userMapper.modifyUserInfo(userModel);

        return userModel;
    }

    // 유저번호로 유저아이디 가져오기
    @Override
    public String getUserId(Long user_no) {
        return userMapper.getUserId(user_no);
    }

    // 유저 활성, 비활성
    @Override
    public String userManagement(UserDTO userDTO) {
        Boolean user_activated = userDTO.getUser_activated();
        user_activated = !user_activated;

        UserModel userModel = UserModel.builder()
                .user_activated(user_activated)
                .user_id(userDTO.getUsername())
                .build();

        userMapper.userManagement(userModel);

        return user_activated ? "활성 완료." : "비활성 완료.";
    }

    @Transactional(readOnly = true)
    public Optional<UserModel> getUserWithAuthorities(String username) { // 특정 유저의 정보와 권한 확인
        return this.findOneWithAuthoritiesByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<UserModel> getMyUserWithAuthorities() { // 현재 SecurityContext 에 저장돼 있는 유저의 정보와 권한 확인
        return SecurityUtil.getCurrentUsername().flatMap(this::findOneWithAuthoritiesByUsername);
    }

    // 유저정보와 유저권한들을 가져옴
    public Optional<UserModel> findOneWithAuthoritiesByUsername(String username) {
        return userMapper.findOneUserByUsername(username)
                .map(UserModel -> {
                    addAuthorities(username, UserModel);
                    return UserModel;
                });
    }


    private void addAuthorities(String username, UserModel userModel) { //유저권한 정보를 가져와서 userModel 에 세팅

        if (userMapper.findOneAuthoritiesByUsername(username) == null) {
            return;
        }
        userModel.setAuthorities(userMapper.findOneAuthoritiesByUsername(username));
    }

    private void saveUser(UserModel userModel) {
        userMapper.save(userModel);
        userMapper.authSave(userModel);
    }
}
