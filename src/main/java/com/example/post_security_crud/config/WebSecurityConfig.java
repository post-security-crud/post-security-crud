package com.example.post_security_crud.config;

import com.example.post_security_crud.jwt.JwtAuthFilter;
import com.example.post_security_crud.jwt.JwtUtil;
import com.example.post_security_crud.security.CustomSecurityFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest; //*저절로 추가됨
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@EnableGlobalMethodSecurity(securedEnabled = true) // @Secured 어노테이션 활성화
@RequiredArgsConstructor
public class WebSecurityConfig {

    // JwtUtil 주입해주는거 맞는지 확인하기
    private final JwtUtil jwtUtil;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // h2-console 사용 및 resources 접근 허용 설정
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toH2Console())
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF 설정
        http.csrf().disable();

        //기본 설정인 Session 방식은 사용하지 않고 JWT 방식을 사용하기 위한 설정
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http.authorizeRequests().antMatchers("/api/user/**").permitAll()  // "api/user" 로 들어온 요청 모두 허가(회원가입,로그인은 인증 필요없으므로)
                              .anyRequest().authenticated() //그 외 URL요청들은 모두 Authentication 인증 처리 하겠다
                                 //JWT 인증/인가를 사용하기 위한 설정
                             .and().addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
//         // 위에꺼 아니면 이거
//        http.authorizeRequests()
//                .anyRequest().authenticated();

           // 아니면 이거
//        http.authorizeRequests().
//                antMatchers(HttpMethod.GET,"/api/post").permitAll()  <- permit할 주소 넣어주기
//                .anyRequest().authenticated();


        // 로그인 사용 -> 로그인화면 반환 할 거 없으니 주석처리
//        http.formLogin();


        // Custom Filter 등록하기 ( jwt토큰을 검증할 것이므로 따로 만들어서 Filter 앞에 추가해주는 것)
        // => 만든 CustomSecurityFilter를 UsernamePasswordAuthenticationFilter 이전에 실행하도록 해주는 것
        // Custom Filter에서 인증이 완료되어서 authentication이 넘어가므로(context로) UsernamePasswordAuthenticationFilter도 그냥 통과됨)
        // JWT 인증/인가 사용할 것이므로 주석처리
//        http.addFilterBefore(new CustomSecurityFilter(userDetailsService, passwordEncoder()), UsernamePasswordAuthenticationFilter.class);

        //exceptionHandling때 사용
//        http.exceptionHandling().accessDeniedPage("/api/user/forbidden");

        return http.build();
    }

}