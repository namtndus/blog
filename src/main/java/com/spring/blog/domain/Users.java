package com.spring.blog.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String password;

    @OneToMany(mappedBy = "users")
    List<Subject> subjectList = new ArrayList<Subject>();


}
