package com.example.post_security_crud.dto;

public class ResponseDto {
    private int statusCode;

    private String msg;

    public ResponseDto(String msg, int statusCode){
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
