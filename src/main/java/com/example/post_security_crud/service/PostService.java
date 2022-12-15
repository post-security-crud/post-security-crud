package com.example.post_security_crud.service;

import com.example.post_security_crud.dto.PostResponseDto;
import com.example.post_security_crud.entity.Post;
import com.example.post_security_crud.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostResponseDto> getPostList() {

        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        if(postList.isEmpty()){
            throw new NullPointerException("게시글이 존재하지 않습니다.");
        }

        List<PostResponseDto> posts = new ArrayList<>();
        for (Post post : postList) {
            posts.add(new PostResponseDto(post));
        }
        return posts;
    }

    @Transactional
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("게시글이 존재하지 않습니다.")
        );
      return new PostResponseDto(post);
    }
}
