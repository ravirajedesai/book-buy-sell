package com.example.book_service.repository;

import com.example.book_service.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {

    Optional<Book> findByBookName(String bookName);
}
