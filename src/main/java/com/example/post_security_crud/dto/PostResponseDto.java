package com.example.post_security_crud.dto;

import com.example.post_security_crud.entity.Post;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
public class PostResponseDto extends ResponseDto {
    private Long id;

    private String title;

    private String content;

    private String username;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.username = post.getUsername();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}