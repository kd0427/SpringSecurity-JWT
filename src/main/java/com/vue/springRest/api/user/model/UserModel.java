package com.vue.springRest.api.user.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserModel {

    private Long user_no;//user PK

    private String user_id;
    private String user_pwd;
    private String user_name;

    private String user_email;
    private String user_addr;
    private String user_phone;
    private String user_date;

    private boolean user_activated;

}
