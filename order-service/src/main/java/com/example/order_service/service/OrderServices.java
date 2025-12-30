package com.example.order_service.service;

import com.example.order_service.entity.Order;

import java.util.List;

public interface OrderServices {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    void deleteOrderById(Long id);
    Order addOrders(Order order);
}
