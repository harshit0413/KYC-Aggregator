package com.exception;

import lombok.*;

@Getter
public class CustomException extends RuntimeException {
    private final String errorKey;

    public CustomException(String errorKey) {
        super(errorKey);
        this.errorKey = errorKey;
    }

}