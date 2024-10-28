package com.spring.blog.domain;

import jakarta.persistence.*;

@Table(name = "article")
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
}
