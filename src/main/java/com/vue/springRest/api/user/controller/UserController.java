package com.vue.springRest.api.user.controller;

import com.vue.springRest.api.dto.UserDTO;
import com.vue.springRest.api.user.model.UserModel;
import com.vue.springRest.api.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @PutMapping("/modify")
    public ResponseEntity<UserModel> modify(@Valid @RequestBody UserDTO userDTO){

        return ResponseEntity.ok(userService.modifyUserInfo(userDTO));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
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

    //UserList가져오기
    @GetMapping("/allUser")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<UserModel>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    //User 활성 , 비활성
    @PutMapping("/userManagement")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> userManagement(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.userManagement(userDTO));
    }

}

