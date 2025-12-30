package com.example.order_service.controller;

import com.example.order_service.entity.Order;
import com.example.order_service.service.OrderServices;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderServices services;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(services.getAllOrders());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok(services.getOrderById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        services.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/name")
    public ResponseEntity<Order> addOrders(@RequestBody Order order) {
        Order orderCreated=services.addOrders(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderCreated);
    }
}
