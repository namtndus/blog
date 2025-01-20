package com.spring.blog.controller;

import com.spring.blog.domain.Blog;
import com.spring.blog.domain.Users;
import com.spring.blog.dto.CreateBlogDto;
import com.spring.blog.dto.CustomUserDetails;
import com.spring.blog.repository.BlogRepository;
import com.spring.blog.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
public class BlogController {

    private final BlogRepository blogRepository;
    private final UsersRepository usersRepository;

    public BlogController(BlogRepository blogRepository, UsersRepository usersRepository) {
        this.blogRepository = blogRepository;
        this.usersRepository = usersRepository;
    }

    @PostMapping("/blog")
    public String createBlog(@RequestBody CreateBlogDto blogNameDto) {

        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Users users = usersRepository.findByUserId(principal.getUsername()).get();
        log.info("사용자의 이름 : {}",users.getUserId());
        Blog blog = Blog.builder().blogName(blogNameDto.getBlogName())
                .blogDescription(blogNameDto.getBlogDescription())
                .users(users)
                .build();

        blogRepository.save(blog);
        return "good";
    }
}
