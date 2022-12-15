package com.example.post_security_crud.controller;

import com.example.post_security_crud.dto.PostRequestDto;
import com.example.post_security_crud.dto.ResponseDto;
import com.example.post_security_crud.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PutMapping("post/{id}")
    public ResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, HttpServletRequest request) {
        return postService.updatePost(id, postRequestDto, request);
    }

    @DeleteMapping("post/{id}")
    public ResponseDto deletePost(@PathVariable Long id, HttpServletRequest request) {
        return postService.deletePost(id, request);
    }
}
