package com.example.post_security_crud.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String username;

    public Post(PostRequestDto requestDto, String username) {
        super();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.username = username;
    }

    public void update(PostRequestDto postRequestDto) {
        this.content = postRequestDto.getContent();
    }
}

