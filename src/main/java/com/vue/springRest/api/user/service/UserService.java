package com.vue.springRest.api.user.service;

import com.vue.springRest.api.user.model.UserModel;

import java.util.List;

public interface UserService {

   List<UserModel> userList();
   void userJoin(UserModel userModel);
}
