package com.spring.blog.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer countArticle;

    @ManyToOne
    @JoinColumn(name = "blog_pk")
    private Blog blog;

    @ManyToOne
    @JoinColumn(name = "category_pk")
    private Category category;

    @OneToMany
    private List<Article> articles;
}
