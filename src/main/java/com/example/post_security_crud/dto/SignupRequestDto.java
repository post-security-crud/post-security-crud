package com.example.post_security_crud.dto;

import com.example.post_security_crud.entity.UserRoleEnum;

public class SignupRequestDto {
    private String username;

    private String password;

    private UserRoleEnum role;
}
