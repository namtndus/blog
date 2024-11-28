package com.spring.blog.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 아래에 있는 것은 무엇일까요?
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private UserController userController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testExample() throws Exception {
        mockMvc.perform(get("/test"))
                .andExpect(status().isOk());
    }

    // 1. 회원가입
    // 2. 로그인
    // 3. 로그아웃
    // 5. 회원 탈퇴

}