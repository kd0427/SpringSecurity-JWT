package com.vue.springRest.api.auth.controller;

import com.vue.springRest.api.dto.LoginDTO;
import com.vue.springRest.api.dto.TokenDTO;
import com.vue.springRest.api.dto.UserDTO;
import com.vue.springRest.jwt.JwtFilter;
import com.vue.springRest.jwt.TokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@AllArgsConstructor // 모든 필드변수를 매개변수로 갖는 생성자 메소드 생성해줌
public class AuthController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

//    public AuthController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder){
//        this.tokenProvider = tokenProvider;
//        this.authenticationManagerBuilder = authenticationManagerBuilder;
//    }

    @PostMapping("/login")
    //ResponseEntity 는 사용자의 HttpRequest 에 대한 응답 데이터를 포함하는 클래스이다.
    // 따라서 HttpStatus, HttpHeaders, HttpBody 를 포함한다.
    public ResponseEntity<Map<String,String>> authorize(@Valid @RequestBody LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);



        String jwt = tokenProvider.createToken(authentication);
        UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
        String username = springSecurityUser.getUsername();

        Map<String,String> result = new HashMap<>();
        result.put("token",jwt);
        result.put("username",username);





        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer "+ jwt);

        return new ResponseEntity<>(result, httpHeaders, HttpStatus.OK);

    }
}

