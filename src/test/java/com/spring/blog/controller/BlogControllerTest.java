package com.spring.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.blog.account.TestAccount;
import com.spring.blog.dto.CreateBlogDto;
import com.spring.blog.repository.BlogRepository;
import com.spring.blog.repository.UsersRepository;
import com.spring.blog.service.BlogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Todo: 블로그 컨트롤러에서 어떻게 보이는지 확인하는 것
@WebMvcTest(controllers = BlogController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class BlogControllerTest {

    @MockBean
    private BlogService blogService;

    @Autowired
    private ObjectMapper objectMapper;

    //
    @Autowired
    private BlogController blogController;

    @MockBean
    private UsersRepository usersRepository;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BlogRepository blogRepository;
//
//    void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(blogController).build();
//        Users users = Users.builder().userId("admin").nickname("admin").password(bCryptPasswordEncoder.encode("admin"))
//                .role("ROLE_ADMIN").build();
//        Users save = usersRepository.save(users);
//        Blog createBlog = Blog.builder()
//                .users(save)
//                .blogName("개발자")
//                .blogDescription("개발자를 도전하는 이야기")
//                .build();
//        blogRepository.save(createBlog);
//    }

    @TestAccount
    @Test
    void createBlog() throws Exception {

        CreateBlogDto build = CreateBlogDto.builder()
                .blogName("test")
                .blogDescription("test").build();

        mockMvc.perform(post("/blog")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(build))
                        .with(csrf())
        ).andDo(print())
                .andExpect(status().isOk());
    }

    @WithUserDetails(value = "admin", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    void deleteBlog() throws Exception {

    }
}