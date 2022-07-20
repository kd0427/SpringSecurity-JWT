package com.vue.springRest.api.auth.controller;

import com.vue.springRest.api.auth.mapper.AuthMapper;
import com.vue.springRest.api.auth.service.AuthService;
import com.vue.springRest.api.user.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/auth/{username}")
    public String findById(@PathVariable("username") String username){

        UserModel userInfo = authService.getLoginUserInfo(username);

        String id = userInfo.getUser_id();
        String name = userInfo.getUser_name();

        return id+" "+name;

    }




}
