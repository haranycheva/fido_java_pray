package com.example.demo.errorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandler {

@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex) {
    FieldError fieldError = (FieldError) ex.getBindingResult().getAllErrors().get(0);

    String errorMessage = fieldError.getDefaultMessage();

    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), errorMessage);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
}
}