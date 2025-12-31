package com.example.order_service.controller;

import com.example.order_service.entity.Order;
import com.example.order_service.service.OrderServices;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<Order>>
            getAllOrders(@RequestParam int pageNo,
                         @RequestParam int pageSize,
                         @RequestParam String sortBy,
                         @RequestParam String sortDir){
        return ResponseEntity.ok(services.getAllOrders(pageNo,pageSize,sortBy,sortDir));
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
