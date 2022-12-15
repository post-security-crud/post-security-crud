package com.example.post_security_crud.controller;

import com.example.post_security_crud.dto.PostRequestDto;
import com.example.post_security_crud.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")                     // 클라이언트로 부터 요청을 받음.                        // 클래스 타입 선언 가능
    public PostResponseDto savePost(@RequestBody PostRequestDto requestDto, HttpServletRequest request) {                     // public 접근제한자 ResponseDto 리턴타입 Body값들을 들고 오겠다고 선언
        return postService.savePost(requestDto, request);              // Service에서 받아 Client로 전달.
    }

    @GetMapping("/posts")
    public List<PostResponseDto> getPostList(){
        return postService.getPostList();

    }

    @GetMapping("/post/{Id}")
    public PostResponseDto getPost(@PathVariable Long Id){
       return postService.getPost(Id);
    }

    @PutMapping("post/{id}")
    public ResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, HttpServletRequest request) {
        return postService.updatePost(id, postRequestDto, request);
    }

    @DeleteMapping("post/{id}")
    public ResponseDto deletePost(@PathVariable Long id, HttpServletRequest request) {
        return postService.deletePost(id, request);
    }
}
