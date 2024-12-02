package com.spring.blog.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentPk;

    @Column(nullable = false)
    private String commentAuthor;

    @Column(nullable = false)
    private String commentText;

    @Column(nullable = false)
    private Boolean isUpdated;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(nullable = false)
    private LocalDateTime updated;
}
