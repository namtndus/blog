package com.spring.blog.repository;

import com.spring.blog.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByUserId(String userId);

    boolean existsByUserId(String userId);
    void deleteByUserId(String userId);
}
