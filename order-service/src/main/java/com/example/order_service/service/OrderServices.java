package com.example.order_service.service;

import com.example.order_service.entity.Order;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderServices {
    Page<Order> getAllOrders(int pageNo,
                             int pageSize,
                             String sortBy,
                             String sortDir);
    Order getOrderById(Long id);
    void deleteOrderById(Long id);
    Order addOrders(Order order);
}
