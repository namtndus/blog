package com.spring.blog.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likePk;

    @ManyToOne
    @JoinColumn(name = "users_pk")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "article_pk")
    private Article article;

}
