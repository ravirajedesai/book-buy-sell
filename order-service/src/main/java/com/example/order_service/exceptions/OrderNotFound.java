package com.example.order_service.exceptions;

public class OrderNotFound extends RuntimeException{
    public OrderNotFound(String message) {
        super(message);
    }
}
