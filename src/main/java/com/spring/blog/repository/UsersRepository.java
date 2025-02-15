package com.spring.blog.repository;

import com.spring.blog.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    boolean existsByUserId(String userId);
    void deleteByUserId(String userId);
    Optional<Users> findByUserId(String userId);
}
