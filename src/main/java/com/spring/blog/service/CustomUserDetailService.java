package com.spring.blog.service;

import com.spring.blog.domain.Users;
import com.spring.blog.dto.CustomUserDetails;
import com.spring.blog.dto.LoginDto;
import com.spring.blog.dto.SignupDto;
import com.spring.blog.repository.UsersRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.password.CompromisedPasswordException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.DeferredSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Slf4j
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    // manager가 아니라 builder를 사용해야 한다. 알게 된 이유, 순환 참조가 발생해서이다
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    private final SecurityContextRepository securityContextRepository;

    public CustomUserDetailService(UsersRepository usersRepository, BCryptPasswordEncoder passwordEncoder, AuthenticationManagerBuilder authenticationManagerBuilder, SecurityContextRepository securityContextRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.securityContextRepository = securityContextRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users userData = usersRepository.findByUserId(username);

        if (userData == null) {
            throw new UsernameNotFoundException(username);
        }

        return new CustomUserDetails(userData);
    }

    // 회원가입
    public boolean signup(SignupDto signupDto) {
        boolean isChecked = usersRepository.existsByUserId(signupDto.getUserId());
        if (isChecked) {
            return false;
        }

        Users users = new Users();
        users.setUserId(signupDto.getUserId());
        users.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        users.setNickname(signupDto.getNickname());
        users.setRole("ROLE_USER");

        usersRepository.save(users);
        return true;
    }

    // 로그인
    public boolean login(LoginDto loginDto,HttpServletRequest req, HttpServletResponse res) {
        UserDetails userDetails = loadUserByUsername(loginDto.getUsername());

        log.info("비밀번호 비교 결과값: {}", passwordEncoder.matches(loginDto.getPassword(), userDetails.getPassword()));

        if(!passwordEncoder.matches(loginDto.getPassword(), userDetails.getPassword())) {
            throw new CompromisedPasswordException(loginDto.getPassword());
        }
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), loginDto.getPassword(), userDetails.getAuthorities());
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(token);
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);
            securityContextRepository.saveContext(context,req,res);
            //RequestContextHolder.currentRequestAttributes().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,context, RequestAttributes.SCOPE_SESSION);
        }catch (Exception e){
            e.printStackTrace();
        }

        log.info("로그인 서비스 종료");
        return true;
    }

    // 로그아웃
    public boolean logout(HttpServletRequest request, HttpServletResponse response){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication != null){
                new SecurityContextLogoutHandler().logout(request, response, authentication);
            }

            Cookie[] cookies = request.getCookies();

            for (Cookie cookie : cookies) {
                log.info("쿠키들의 key : {}", cookie.getName());
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        log.info("로그아웃");
        return true;
    }

    // delete 문에서는 아래 어노테이션을 붙여야 실행이 될까?
    @Transactional
    // 회원 탈퇴
    public boolean signout(HttpServletRequest req, HttpServletResponse res){
        log.info("회원 탈퇴 서비스 시작");

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
            log.info("회원 탈퇴에서의 이름 : {}", principal.getUsername());
            if(authentication != null){
                new SecurityContextLogoutHandler().logout(req, res, authentication);
            }

            Cookie[] cookies = req.getCookies();

            for (Cookie cookie : cookies) {
                log.info("쿠키들의 key : {}", cookie.getName());
                cookie.setMaxAge(0);
                res.addCookie(cookie);
            }
            usersRepository.deleteByUserId(principal.getUsername());
            log.info("회원 탈퇴 서비스 종료");
        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }
}
