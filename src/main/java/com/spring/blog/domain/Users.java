package com.spring.blog.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String username;

    private String nickname;

    private String password;

    private String role;

    @OneToMany(mappedBy = "users")
    List<Subject> subjectList = new ArrayList<Subject>();


}
