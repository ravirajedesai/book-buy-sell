package com.example.book_service.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandlerBookNotFound {
    @ExceptionHandler(BookNotFound.class)
    public ResponseEntity<Map<String, Object>> BookNotFoundGlobalHandler(BookNotFound ex){
        Map<String ,Object>response=new HashMap<>();
        response.put("Message",ex.getMessage());
        response.put("error",404);
        response.put("Time", LocalDateTime.now());
        return ResponseEntity.ok(response);
    }
}
