package com.spring.blog.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Blog extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blogPk;

    @Column(nullable = false)
    private String blogName;

    private String blogDescription;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime created;

    @ManyToOne
    @JoinColumn(name = "users_pk")
    private Users users;

    @OneToMany(mappedBy = "blog")
    List<Board> articles = new ArrayList<>();
}
