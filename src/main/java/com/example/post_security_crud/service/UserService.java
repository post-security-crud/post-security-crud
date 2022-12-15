package com.example.post_security_crud.service;

import com.example.post_security_crud.dto.LoginRequestDto;
import com.example.post_security_crud.dto.ResponseDto;
import com.example.post_security_crud.dto.SignupRequestDto;
import com.example.post_security_crud.entity.User;
import com.example.post_security_crud.entity.UserRoleEnum;
import com.example.post_security_crud.jwt.JwtUtil;
import com.example.post_security_crud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private static final String ADMIN_TOKEN = "";


    @Transactional
    public ResponseDto signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        Optional<User> usernameCheck = userRepository.findByUsername(username);
        if (usernameCheck.isPresent()){
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, role);
        userRepository.save(user);

        return new ResponseDto(200, "회원가입이 완료되었습니다.");
    }

    @Transactional(readOnly = true)
    public ResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByUsername(loginRequestDto.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );

        if (!user.getPassword().equals(loginRequestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));
        return new ResponseDto(200, "로그인이 완료되었습니다.");
    }
}
