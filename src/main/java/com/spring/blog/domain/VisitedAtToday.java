package com.spring.blog.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class VisitedAtToday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long visitedAtTodayPk;

    @Column(nullable = false)
    private String clientIP;

    @Column(nullable = false)
    private LocalDateTime visitDate;
}
