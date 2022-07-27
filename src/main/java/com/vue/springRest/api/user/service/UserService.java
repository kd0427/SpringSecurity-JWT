package com.vue.springRest.api.user.service;

import com.vue.springRest.api.dto.UserDTO;
import com.vue.springRest.api.user.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserService {

   List<UserModel> userList();


}
