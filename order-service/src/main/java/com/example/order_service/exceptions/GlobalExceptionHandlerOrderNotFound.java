package com.example.order_service.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandlerOrderNotFound {

    @ExceptionHandler(OrderNotFound.class)
    public ResponseEntity<Map<String,Object>> orderNotFoundGlobalHandler(OrderNotFound ex){
        Map<String ,Object> response=new HashMap<>();
        response.put("Message",ex.getMessage());
        response.put("error",404);
        response.put("Time", LocalDateTime.now());
        return ResponseEntity.ok(response);
    }
}
