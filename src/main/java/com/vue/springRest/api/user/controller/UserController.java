package com.vue.springRest.api.user.controller;

import com.vue.springRest.api.user.model.UserModel;
import com.vue.springRest.api.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping("/user")
    public List<UserModel> userList(){

        return userService.userList();
    }

    @PostMapping("/user")
    public String userJoin(@RequestBody UserModel userModel){
        userService.userJoin(userModel);

        return "방금 회원가입한 사람 ID = "+userModel.getUser_id();
    }

}
