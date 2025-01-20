package com.spring.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.blog.domain.Blog;
import com.spring.blog.domain.Users;
import com.spring.blog.dto.CreateBlogDto;
import com.spring.blog.repository.BlogRepository;
import com.spring.blog.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Todo: 블로그 컨트롤러에서 어떻게 보이는지 확인하는 것
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class BlogControllerTest {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    //
    @Autowired
    private BlogController blogController;

    @Autowired
    private UsersRepository usersRepository;


    private MockMvc mockMvc;
    @Autowired
    private BlogRepository blogRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(blogController).build();
        Users users = Users.builder().userId("admin").nickname("admin").password(bCryptPasswordEncoder.encode("admin"))
                .role("ROLE_ADMIN").build();
        Users save = usersRepository.save(users);
        Blog createBlog = Blog.builder()
                .users(save)
                .blogName("개발자")
                .blogDescription("개발자를 도전하는 이야기")
                .build();
        blogRepository.save(createBlog);
    }

    @WithUserDetails(value = "admin", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    void createBlog() throws Exception {

        CreateBlogDto build = CreateBlogDto.builder()
                .blogName("test")
                .blogDescription("test").build();

        mockMvc.perform(post("/blog")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(build))
        ).andDo(print())
                .andExpect(status().isOk());
    }

    @WithUserDetails(value = "admin", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    void deleteBlog() throws Exception {

    }
}