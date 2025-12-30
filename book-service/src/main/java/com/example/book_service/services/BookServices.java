package com.example.book_service.services;

import com.example.book_service.dto.BookResponse;
import com.example.book_service.entity.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookServices {
    Page<Book> getAllBooks(int pageNo,
                           int pageSize,
                           String sortBy,
                           String sortDir);
    Book getBookById(Long id);
    void deleteBookById(Long id);
    Book addBook(Book book);

    BookResponse getBookByBookName(String bookName);

    BookResponse reduceBookQuantity(String bookName, int bookQuantity);
}
