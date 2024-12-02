package com.spring.blog.repository;

import com.spring.blog.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);

    boolean existsByUsername(String username);
    void deleteByUsername(String username);
}
