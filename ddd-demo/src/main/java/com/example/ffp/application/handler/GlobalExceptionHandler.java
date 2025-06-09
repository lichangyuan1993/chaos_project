package com.example.ffp.application.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 处理嵌套对象校验失败
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        
        Map<String, String> errors = new HashMap<>();
        
        ex.getBindingResult().getAllErrors().forEach(error -> {
            if (error instanceof FieldError fieldError) {

                // 获取嵌套字段路径（如：address.city）
                String fieldName = fieldError.getField();

                String errorMessage = fieldError.getDefaultMessage();
                
                errors.put(fieldName, errorMessage);
            } else {
                errors.put(error.getObjectName(), error.getDefaultMessage());
            }
        });
        
        return ResponseEntity.badRequest().body(errors);
    }
}