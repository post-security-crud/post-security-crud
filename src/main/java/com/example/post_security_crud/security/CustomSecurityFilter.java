package com.example.post_security_crud.security;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class CustomSecurityFilter extends OncePerRequestFilter {

    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;


    //filterchain 받아서 filter 끼리 이동
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("request.getRequestURI() = " + request.getRequestURI());


        if(username != null && password  != null && (request.getRequestURI().equals("/api/user/login") || request.getRequestURI().equals("/api/test-secured"))){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);    //user객체, 이름, pw 있음

            // 비밀번호 확인
            if(!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new IllegalAccessError("비밀번호가 일치하지 않습니다.");
            }

            // 인증 객체 생성 및 등록
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); //위에서 pw 검증이 끝났으므로 credentail에는 null을 넣어준다
            context.setAuthentication(authentication);

            SecurityContextHolder.setContext(context);
        }

        //request와 response를 담아서 다음Filter로 이동시켜줌 & 예외 발생시 이전 filter로 예외가 넘어감
        filterChain.doFilter(request,response);
    }
}
