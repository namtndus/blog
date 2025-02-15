package com.spring.blog.service;

import com.spring.blog.domain.Blog;
import com.spring.blog.domain.Users;
import com.spring.blog.dto.CreateBlogDto;
import com.spring.blog.dto.CustomUserDetails;
import com.spring.blog.repository.BlogRepository;
import com.spring.blog.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final UsersRepository usersRepository;

    public BlogService(BlogRepository blogRepository, UsersRepository usersRepository) {
        this.blogRepository = blogRepository;
        this.usersRepository = usersRepository;
    }

    public void createBlog(CreateBlogDto createBlogDto){
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Users users = usersRepository.findByUserId(principal.getUsername()).get();
        log.info("사용자의 이름 : {}",users.getUserId());
        Blog blog = Blog.builder().blogName(createBlogDto.getBlogName())
                .blogDescription(createBlogDto.getBlogDescription())
                .users(users)
                .build();

        blogRepository.save(blog);
    }
}
