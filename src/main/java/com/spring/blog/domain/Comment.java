package com.spring.blog.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
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

    @ManyToOne
    @JoinColumn(name = "article_pk")
    private Article article;

}
