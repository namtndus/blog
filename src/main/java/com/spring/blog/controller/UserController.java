package com.spring.blog.controller;

import com.spring.blog.dto.CustomUserDetails;
import com.spring.blog.dto.LoginDto;
import com.spring.blog.dto.SignupDto;
import com.spring.blog.dto.response.ResponseDto;
import com.spring.blog.service.CustomUserDetailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserController {

    private final CustomUserDetailService customUserDetailService;

    public UserController(CustomUserDetailService customUserDetailService) {
        this.customUserDetailService = customUserDetailService;
    }

    // 테스트
    @GetMapping("/test")
    public ResponseEntity<ResponseDto> test() {
        log.info("인증 테스트 이름 : {}", SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseDto.builder()
                        .status("success")
                        .message("테스트 성공하였습니다.")
                        .build()
        );
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody LoginDto loginDto, HttpServletResponse response, HttpServletRequest request) {
        log.info("로그인 출발");
        customUserDetailService.login(loginDto,request, response);
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.builder()
                        .status("success")
                        .message("로그인에 성공하였습니다.")
                .build());
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@RequestBody SignupDto signupDto) {
        customUserDetailService.signup(signupDto);
        log.info("회원가입 성공");
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.builder()
                        .status("success")
                        .message("회원가입에 성공하였습니다")
                .build()
        );
    }

    @GetMapping("/logout")
    public ResponseEntity<ResponseDto> logout(HttpServletRequest request, HttpServletResponse response) {
        log.info("로그아웃 시작");
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("이름 : {}",name);
        log.info("비밀번호 : {}",principal.getPassword());
        customUserDetailService.logout(request, response);
        log.info("로그아웃 종류");
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseDto.builder()
                        .status("success")
                        .message("로그아웃에 성공하였습니다")
                        .build()
        );
    }

    @DeleteMapping("/profile")
    public ResponseEntity<ResponseDto> profile(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseDto.builder()
                        .status("success")
                        .message("탈퇴가 되었습니다")
                        .build()
        );
    }
}
