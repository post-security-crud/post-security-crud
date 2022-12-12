package com.example.post_security_crud.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PostResponseDto extends ResponseDto {
    private Long id;

    private String title;

    private String content;

    private String username;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
}