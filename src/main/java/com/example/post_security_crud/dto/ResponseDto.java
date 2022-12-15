package com.example.post_security_crud.dto;

public class ResponseDto {
    private int statusCode;

    private String msg;

    public ResponseDto(int statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }
}
