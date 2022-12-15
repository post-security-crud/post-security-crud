package com.example.post_security_crud.dto;

import lombok.Getter;

/*
Getter 가 없으면 다른 곳에서 값을 가져다 사용할 수 없음.
Setter 가 없으면 @RequestBody 맵핑이 안될까??
 */

@Getter
public class PostRequestDto {
    private String title;

    private String content;

    private String username;

}
