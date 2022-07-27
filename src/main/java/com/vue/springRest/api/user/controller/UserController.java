package com.vue.springRest.api.user.controller;

import com.vue.springRest.api.dto.UserDTO;
import com.vue.springRest.api.user.model.UserModel;
import com.vue.springRest.api.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api")
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("/signup")
    public ResponseEntity<UserModel> signup(@Valid @RequestBody UserDTO userDTO){
        return  ResponseEntity.ok(userService.signup(userDTO)); //status 가 200ok
    }

    @GetMapping("/user")
// 이 메소드가 실행되기전에 권한이 있는지 검사하는 어노테이션
    public ResponseEntity<UserModel> getMyUserInfo(){
        return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserModel> getUserInfo(@PathVariable String username){
        if(userService.getUserWithAuthorities(username).isPresent()){
            return ResponseEntity.ok(userService.getUserWithAuthorities(username).get());
        }
        return ResponseEntity.notFound().build();
    }
}

