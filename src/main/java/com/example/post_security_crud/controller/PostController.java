package com.example.post_security_crud.controller;

import com.example.post_security_crud.dto.PostRequestDto;
import com.example.post_security_crud.dto.PostResponseDto;
import com.example.post_security_crud.dto.ResponseDto;
import com.example.post_security_crud.entity.Post;
import com.example.post_security_crud.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

//    @PostMapping("/post")
//    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto, HttpServletRequest request) {
//        return postService.createPost(postRequestDto, request);
//    }
    @PostMapping("/post")                     // 클라이언트로 부터 요청을 받음.                        // 클래스 타입 선언 가능
    public PostResponseDto savePost(@RequestBody PostRequestDto requestDto, HttpServletRequest request) {                     // public 접근제한자 ResponseDto 리턴타입 Body값들을 들고 오겠다고 선언
    //        ResponseDto responseDto = boardService.saveBoard(requestDto);                           // 컨트롤러에서 서비스를 호출, 전달.
    //        return responseDto;                                                                     // 클라이언트에게 값 전달.
        return postService.savePost(requestDto, request);              // Service에서 받아 Client로 전달.
    }
    @GetMapping("/post/{id}")
    public PostResponseDto detailPost(@PathVariable Long id) {
        return postService.detailPost(id);                        // Service에서 받아 Client로 전달.
    }
    @GetMapping("/posts")
    public List<PostResponseDto> listPost() {
        return postService.listPost();
    }

    @PutMapping("/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        return postService.updatePost(id, requestDto, request);
    }

    @DeleteMapping("/post/{id}")
    public ResponseDto deletePost(@PathVariable Long id, HttpServletRequest request) {
        return postService.deletePost(id, request);
    }

}
