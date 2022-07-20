package com.vue.springRest.api.auth.mapper;

import com.vue.springRest.api.user.model.UserModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface AuthMapper {
    UserModel getLoginUserInfo(String username);
}
