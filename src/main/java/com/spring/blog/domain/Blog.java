package com.spring.blog.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blogPk;

    @Column(nullable = false)
    private String blogName;

    private String blogDescription;

    @Column(nullable = false)
    private LocalDateTime created;
}
