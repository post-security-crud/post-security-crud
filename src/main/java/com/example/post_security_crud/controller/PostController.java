package com.example.post_security_crud.controller;

import com.example.post_security_crud.dto.PostRequestDto;
import com.example.post_security_crud.dto.ResponseDto;
import com.example.post_security_crud.service.PostService;
import com.example.post_security_crud.dto.PostResponseDto;
import com.example.post_security_crud.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

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
