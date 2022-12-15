package com.example.post_security_crud.security;

import com.example.post_security_crud.entity.User;
import com.example.post_security_crud.entity.UserRoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;



//인증이 완료된 사용자 추가
public class UserDetailsImpl implements UserDetails {

    private final User user;    //인증 완료된 User 객체
    private final String username;     //인증 완료된 User의 username

    public UserDetailsImpl(User user, String username){
        this.user = user;
        this.username = username;
    }

    public User user() {         //인증 완료된 User를 가져오는 Getter
        return user;
    }

    //user권한 가지고와서 추상화해서 담기
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        UserRoleEnum role = user.getRole();           //user권한 갖고와서
        String authority = role.getAuthority();         //String값으로 만들고

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);  //추상화하여 사용
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);

        return authorities;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


}
