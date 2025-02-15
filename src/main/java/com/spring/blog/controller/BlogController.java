package com.spring.blog.controller;

import com.spring.blog.domain.Blog;
import com.spring.blog.domain.Users;
import com.spring.blog.dto.CreateBlogDto;
import com.spring.blog.dto.CustomUserDetails;
import com.spring.blog.dto.response.ResponseDto;
import com.spring.blog.repository.BlogRepository;
import com.spring.blog.repository.UsersRepository;
import com.spring.blog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/blog")
    public ResponseEntity<ResponseDto> createBlog(@RequestBody CreateBlogDto blogNameDto) {
        blogService.createBlog(blogNameDto);

        ResponseDto response = ResponseDto.builder()
                .message("블로그가 생성이 되었습니다")
                .status("success")
                .build();
        return ResponseEntity.ok(response);
    }
}
