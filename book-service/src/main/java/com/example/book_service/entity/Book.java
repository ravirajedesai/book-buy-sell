package com.example.book_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;

    @Column(nullable = false,unique = true)
    private String bookName;

    @Column(nullable = false)
    private String bookAuthor;

    @Column(nullable = false)
    private Double bookPrice;

    @Column(nullable = false)
    private Integer bookStock;
}
