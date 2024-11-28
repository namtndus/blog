package com.spring.blog.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message<T> {
    private boolean state;
    private String message;
    private T data;
}
