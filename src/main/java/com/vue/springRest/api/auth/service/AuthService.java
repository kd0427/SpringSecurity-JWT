package com.vue.springRest.api.auth.service;

import com.vue.springRest.api.auth.mapper.AuthMapper;
import com.vue.springRest.api.user.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final AuthMapper authMapper;

    public UserModel getLoginUserInfo(String username){
        return  authMapper.getLoginUserInfo(username);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel userModel = authMapper.getLoginUserInfo(username);



        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));



        return new User(userModel.getUser_id(), userModel.getUser_pwd(),authorities);
    }
}
