package com.example.order_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @Column(nullable = false)
    private String buyerName;

    @Column(nullable = false)
    private String bookName;

    @Column(nullable = false)
    private String bookAuthor;

    @Column(nullable = false)
    private Double bookPrice;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double total;
}
