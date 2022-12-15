package com.example.post_security_crud.dto;

import com.example.post_security_crud.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponseDto extends ResponseDto {
    private Long id;

    private String title;

    private String content;

    private String username;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public PostResponseDto(int statusCode, String msg, Long id, String title, String content, String username, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        super(statusCode, msg);
        this.id = id;
        this.title = title;
        this.content = content;
        this.username = username;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public PostResponseDto(int statusCode, String msg, Post post) {
        super(statusCode, msg);
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.username = post.getUsername();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}