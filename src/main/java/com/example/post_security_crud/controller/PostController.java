package com.example.post_security_crud.controller;

import com.example.post_security_crud.dto.PostResponseDto;
import com.example.post_security_crud.service.PostService;
import lombok.RequiredArgsConstructor;
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
}
