package com.spring.blog.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Table(name = "article")
@Entity
public class Article extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String type;

    private String IsPassword;

    @ManyToOne
    @JoinColumn(name = "board_pk")
    private Board board;

    @OneToMany(mappedBy = "article")
    List<Like> likes = new ArrayList<Like>();

    @OneToMany(mappedBy = "article")
    List<Comment> comments = new ArrayList<Comment>();
}
