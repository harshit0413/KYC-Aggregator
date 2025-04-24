package com.exception;

import com.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {

        ErrorMapper error = ErrorMapperUtil.getError(ex.getErrorKey());

        ErrorResponse response = new ErrorResponse(error.getCustomStatusCode(), error.getErrorMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        ErrorMapper error = ErrorMapperUtil.getError("UNKNOWN_ERROR");

        ErrorResponse response = new ErrorResponse(error.getCustomStatusCode(), error.getErrorMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}