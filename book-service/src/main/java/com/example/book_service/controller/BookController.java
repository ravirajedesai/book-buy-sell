package com.example.book_service.controller;

import com.example.book_service.dto.BookResponse;
import com.example.book_service.entity.Book;
import com.example.book_service.services.BookServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookServices services;

    @GetMapping
    public ResponseEntity<Page<Book>>
                    getAllBooks(@RequestParam(defaultValue = "1") int pageNo,
                                @RequestParam(defaultValue = "5") int pageSize,
                                @RequestParam(defaultValue = "bookName") String sortBy,
                                @RequestParam(defaultValue = "ASC") String sortDir){
        return ResponseEntity.ok(services.getAllBooks(pageNo,pageSize,sortBy,sortDir));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        return ResponseEntity.ok(services.getBookById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        services.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<Book> addBooks(@RequestBody Book book){
        return ResponseEntity.status(HttpStatus.CREATED).body(services.addBook(book));
    }
    @GetMapping("/name/{bookName}")
    public ResponseEntity<BookResponse> getBookByName(@PathVariable String bookName){
        return ResponseEntity.ok(services.getBookByBookName(bookName));
    }
    @PutMapping("/reduceStock/{bookName}/{bookQuantity}")
    public ResponseEntity<BookResponse> reduceBookStock(@PathVariable String bookName,
                                 @PathVariable int bookQuantity){
        return ResponseEntity.ok(services.reduceBookQuantity(bookName, bookQuantity));
    }
}
