package com.spring.blog.account;

import com.spring.blog.domain.Users;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AccountTemplate {

    private static PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public static final Users USERS = Users.builder()
            .nickname("Nickname")
            .userId("namtndus")
            .password(passwordEncoder.encode("12345"))
            .build();
}
