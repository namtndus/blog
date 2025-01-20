package com.spring.blog.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateBlogDto {

    private String blogName;
    private String blogDescription;
}
