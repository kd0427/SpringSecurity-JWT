package com.vue.springRest.api.user.mapper;

import com.vue.springRest.api.user.model.UserModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserModel> userList();
    void userJoin(UserModel userModel);
}
