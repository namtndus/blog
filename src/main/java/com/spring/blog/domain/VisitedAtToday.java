package com.spring.blog.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class VisitedAtToday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long visitedAtTodayPk;

    private String clientIP;
}
