package com.example.post_security_crud.repository;

import com.example.post_security_crud.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
