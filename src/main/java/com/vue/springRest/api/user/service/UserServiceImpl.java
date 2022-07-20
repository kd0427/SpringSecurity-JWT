package com.vue.springRest.api.user.service;

import com.vue.springRest.api.user.mapper.UserMapper;
import com.vue.springRest.api.user.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserModel> userList() {
        return userMapper.userList();
    }

    public void userJoin(UserModel userModel){
        userModel.setUser_pwd(passwordEncoder.encode(userModel.getUser_pwd()));
        userMapper.userJoin(userModel);
    };
}
