package com.vue.springRest.api.user.mapper;

import com.vue.springRest.api.auth.model.Authority;
import com.vue.springRest.api.dto.UserDTO;
import com.vue.springRest.api.user.model.UserModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Mapper
public interface UserMapper {

    List<UserModel> getAllUser();
    void save(UserModel userModel);
    void authSave(UserModel userModel);

    Optional<UserModel> findOneUserByUsername(String user_id);// 유저정보
    Set<Authority> findOneAuthoritiesByUsername(String user_id);//권한정보
    void modifyUserInfo(UserModel userModel);

    String getUserId(Long user_no);

    void userManagement(UserModel userModel); //유저 활성, 비활성

}
