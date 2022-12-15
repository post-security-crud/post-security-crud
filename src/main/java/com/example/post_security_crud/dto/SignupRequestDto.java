package com.example.post_security_crud.dto;

import com.example.post_security_crud.entity.UserRoleEnum;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String username;

    private String password;

    private boolean admin = false;

    private String adminToken = "";
}
