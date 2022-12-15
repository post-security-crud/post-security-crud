package com.example.post_security_crud.security;


import com.example.post_security_crud.entity.User;
import com.example.post_security_crud.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@NoArgsConstructor
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;


    //user가 입력한 username으로 !
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("UserDetailsServiceImpl.loadUserByUsername = " + username);

        User user = userRepository.findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        return new UserDetailsImpl(user, user.getUsername());
    }


}
