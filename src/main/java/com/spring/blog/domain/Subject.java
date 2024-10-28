package com.spring.blog.domain;

import jakarta.persistence.*;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // 회원이 탈퇴 될때, 회원이 작성한 글과 카테고리도 삭제되길 원함(주체)
    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;


}
