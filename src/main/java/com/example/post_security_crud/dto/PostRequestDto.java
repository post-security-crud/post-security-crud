package com.example.post_security_crud.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class PostRequestDto {

    private String title;

    private String content;

}
