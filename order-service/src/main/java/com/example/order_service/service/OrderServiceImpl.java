package com.example.order_service.service;

import com.example.order_service.dto.BookResponse;
import com.example.order_service.entity.Order;
import com.example.order_service.exceptions.OrderNotFound;
import com.example.order_service.feign.BookClient;
import com.example.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderServices{

    private final OrderRepository repository;
    private final BookClient bookClient;

    @Override
    public Page<Order> getAllOrders(int pageNo,
                                    int pageSize,
                                    String sortBy,
                                    String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase("ACS")
                                    ?Sort.by(sortBy).ascending()
                                    :Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);
        return repository.findAll(pageable);
    }

    @Override
    public Order getOrderById(Long id) {
        return repository.findById(id)
                .orElseThrow(()->new OrderNotFound("Order Not Found: "+id));
    }

    @Override
    public void deleteOrderById(Long id) {
        if(!repository.existsById(id)){
            throw new OrderNotFound("Order Not Found To Delete: "+id);
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Order addOrders(Order order) {

        if (order.getBookName() == null || order.getBookName().isBlank()) {
            throw new IllegalArgumentException("Book name is required");
        }

        if (order.getQuantity() == null || order.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        BookResponse response=
                bookClient.reduceBookStock(order.getBookName(),order.getQuantity());

        Order updated=new Order();
        updated.setBuyerName(order.getBuyerName());
        updated.setBookName(response.getBookName());
        updated.setBookAuthor(response.getBookAuthor());
        updated.setBookPrice(response.getBookPrice());
        updated.setQuantity(order.getQuantity());

        double total= response.getBookPrice() * order.getQuantity();
        updated.setTotal(total);

        return repository.save(updated);
    }
}
