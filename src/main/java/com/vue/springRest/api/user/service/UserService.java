package com.vue.springRest.api.user.service;
import com.vue.springRest.api.dto.UserDTO;
import com.vue.springRest.api.user.model.UserModel;
import java.util.List;


public interface UserService {

   // UserList
   List<UserModel> getAllUser();

   //회원가입
   UserModel signup(UserDTO userDTO);

   //유정정보수정
   UserModel modifyUserInfo(UserDTO userDTO);

   // 유저번호로 유저이름 가져오기
   String getUserId(Long user_no);

   // 유저 활성, 비활성
   String userManagement(UserDTO userDTO);

}
