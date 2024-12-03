package com.spring.blog.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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
