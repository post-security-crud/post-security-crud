package com.example.post_security_crud.service;

import com.example.post_security_crud.dto.PostRequestDto;
import com.example.post_security_crud.dto.PostResponseDto;
import com.example.post_security_crud.dto.ResponseDto;
import com.example.post_security_crud.entity.Post;
import com.example.post_security_crud.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public ResponseDto updatePost(Long id, PostRequestDto postRequestDto, HttpServletRequest request) {
        // USER ROLE 체크 & 토큰 & 시큐리티 검증 과정 추가

        Post post = checkPost(id);

        post.update(postRequestDto);

        //TODO
        return new PostResponseDto(200, "OK!", post);
    }

    @Transactional
    public ResponseDto deletePost(Long id, HttpServletRequest request) {
        // USER ROLE 체크 & 토큰 & 시큐리티 검증 과정 추가

        Post post = checkPost(id);

        postRepository.delete(post);

        //TODO
        return new ResponseDto(200, "OK!");
    }

    public Post checkPost(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다.")
        );
    }
}
