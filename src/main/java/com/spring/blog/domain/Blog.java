package com.spring.blog.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "users_pk")
    private Users users;

    @OneToMany(mappedBy = "blog")
    List<Article> articles = new ArrayList<>();
}
