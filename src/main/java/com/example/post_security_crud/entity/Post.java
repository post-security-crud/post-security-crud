package com.example.post_security_crud.entity;

import com.example.post_security_crud.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String content;

    public Post(PostRequestDto requestDto, String username) {
        super();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.username = username;
//        this.id = id; 이게 문제
    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}