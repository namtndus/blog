package com.spring.blog.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "article")
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String content;
    private String type;
    private String IsPassword;
    private LocalDateTime created;
    private LocalDateTime updated;
}
