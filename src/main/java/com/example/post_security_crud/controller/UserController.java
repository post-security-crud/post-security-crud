package com.example.post_security_crud.controller;

import com.example.post_security_crud.dto.LoginRequestDto;
import com.example.post_security_crud.dto.ResponseDto;
import com.example.post_security_crud.dto.SignupRequestDto;
import com.example.post_security_crud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원가입
    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignupRequestDto signupRequestDto){
        return userService.signup(signupRequestDto);
    }

    //로그인
    @PostMapping("/login")
    public ResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        return userService.login(loginRequestDto,response);
    }
}
