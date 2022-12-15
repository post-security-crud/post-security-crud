package com.example.post_security_crud.service;

import com.example.post_security_crud.dto.PostRequestDto;
import com.example.post_security_crud.dto.PostResponseDto;
import com.example.post_security_crud.dto.ResponseDto;
import com.example.post_security_crud.entity.Post;
import com.example.post_security_crud.entity.User;
import com.example.post_security_crud.jwt.JwtUtil;
import com.example.post_security_crud.repository.PostRepository;
import com.example.post_security_crud.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public ResponseDto savePost(PostRequestDto requestDto, HttpServletRequest request) {
        //Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        //토큰이 있는 경우에만 게시글 작성 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                //토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            //토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            //요청 받은 Dto로 DB에 저장할 객체 만들기
            Post post = postRepository.save(new Post(requestDto, user.getUsername()));
            return new PostResponseDto(HttpStatus.OK.value(), "OK!", post);
        } else {
            return new ResponseDto(HttpStatus.BAD_REQUEST.value(), "false");
        }
    }

    public List<PostResponseDto> getPostList() {

        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        if(postList.isEmpty()){
            throw new NullPointerException("게시글이 존재하지 않습니다.");
        }

        List<PostResponseDto> posts = new ArrayList<>();
        for (Post post : postList) {
            posts.add(new PostResponseDto(HttpStatus.OK.value(), "OK!", post));
        }
        return posts;
    }

    @Transactional
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("게시글이 존재하지 않습니다.")
        );
      return new PostResponseDto(HttpStatus.OK.value(), "OK!", post);
    }

    @Transactional
    public ResponseDto updatePost(Long id, PostRequestDto postRequestDto, HttpServletRequest request) {
        // USER ROLE 체크 & 토큰 & 시큐리티 검증 과정 추가
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if(token != null) {
            if(jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            Post post = checkPost(id);

            post.update(postRequestDto);
            return new PostResponseDto(200, "OK!", post);
        }

        return new ResponseDto(400, "False");

    }

    @Transactional
    public ResponseDto deletePost(Long id, HttpServletRequest request) {
        // USER ROLE 체크 & 토큰 & 시큐리티 검증 과정 추가
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if(token != null) {
            if(jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            Post post = checkPost(id);

            postRepository.delete(post);

            return new ResponseDto(200, "OK!");
        }

        return new ResponseDto(400, "False");

    }

    public Post checkPost(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다.")
        );
    }
}
