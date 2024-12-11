package com.spring.blog.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryPk;

    @Column(nullable = false)
    private String categoryName;

    @OneToMany
    private List<Board> board = new ArrayList<Board>();
}
