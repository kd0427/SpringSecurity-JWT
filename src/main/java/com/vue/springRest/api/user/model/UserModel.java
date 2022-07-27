package com.vue.springRest.api.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vue.springRest.api.auth.model.Authority;
import lombok.*;
import org.springframework.lang.Nullable;

import java.util.Set;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    @JsonIgnore
    private Long user_no;//user PK

    private String user_id;

    @JsonIgnore
    private String user_pw;

    private String user_name;

    private String user_email;

    private String user_address;

    private String user_phone;

    private String user_date;

    @JsonIgnore
    private boolean user_activated;

    @Nullable
    private Set<Authority> authorities;

}
