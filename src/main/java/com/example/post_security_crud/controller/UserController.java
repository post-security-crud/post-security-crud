package com.example.post_security_crud.controller;

import com.example.post_security_crud.dto.LoginRequestDto;
import com.example.post_security_crud.dto.ResponseDto;
import com.example.post_security_crud.dto.SignupRequestDto;
import com.example.post_security_crud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignupRequestDto signupRequestDto) {

        userService.signup(signupRequestDto);
        return new ResponseDto(HttpStatus.OK.value(), "회원가입 성공");
    }

    //아래 @AuthenticationPrincipal <- 이거가 굉장히 많은 일을 해준다(코드를 많이 줄여줌)
    @PostMapping("/login")
    public ResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return userService.login(loginRequestDto, response);
    }
}
