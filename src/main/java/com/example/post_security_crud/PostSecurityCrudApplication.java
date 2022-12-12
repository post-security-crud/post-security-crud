package com.example.post_security_crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PostSecurityCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostSecurityCrudApplication.class, args);
	}

}
