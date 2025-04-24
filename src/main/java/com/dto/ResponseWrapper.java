package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseWrapper<T> {
    private String message;
    private T data;
}
