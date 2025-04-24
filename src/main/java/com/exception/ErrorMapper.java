package com.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorMapper {
    private int customStatusCode;
    private String errorMessage;
}