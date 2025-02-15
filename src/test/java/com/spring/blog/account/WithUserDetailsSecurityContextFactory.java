package com.spring.blog.account;

import com.spring.blog.dto.CustomUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithUserDetailsSecurityContextFactory implements WithSecurityContextFactory<TestAccount> {
    @Override
    public SecurityContext createSecurityContext(TestAccount annotation) {
        UserDetails userAccount = new CustomUserDetails(AccountTemplate.USERS);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userAccount, null, userAccount.getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(token);
        return context;
    }
}
