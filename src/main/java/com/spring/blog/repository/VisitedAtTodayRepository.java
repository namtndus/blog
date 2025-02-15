package com.spring.blog.repository;

import com.spring.blog.domain.VisitedAtToday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitedAtTodayRepository extends JpaRepository<VisitedAtToday, Long> {
}
