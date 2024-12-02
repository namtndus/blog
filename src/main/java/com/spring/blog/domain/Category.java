package com.spring.blog.domain;

import jakarta.persistence.*;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryPk;

    @Column(nullable = false)
    private String categoryName;
}
