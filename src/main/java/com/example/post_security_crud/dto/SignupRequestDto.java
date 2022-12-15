package com.example.post_security_crud.dto;

import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String username;
    private String password;

    private boolean admin = false;
    private String adminToken = "";

    public SignupRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
