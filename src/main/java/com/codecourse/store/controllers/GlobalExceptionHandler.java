package com.codecourse.store.controllers;

import com.codecourse.store.dtos.ErrorDto;
import com.codecourse.store.exceptions.CartEmptyException;
import com.codecourse.store.exceptions.CartNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDto> handleUnreadableMessage() {
        return ResponseEntity.badRequest().body(
                new ErrorDto("Invalid request body.")
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException e) {
        var errors = new HashMap<String, String>();
        e.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ErrorDto> handleCartNotFoundException() {
        return ResponseEntity.badRequest().body(
                new ErrorDto("Cart not found.")
        );
    }

    @ExceptionHandler(CartEmptyException.class)
    public ResponseEntity<ErrorDto> handleCartEmptyException() {
        return ResponseEntity.badRequest().body(
                new ErrorDto("Cart is empty.")
        );
    }
}
