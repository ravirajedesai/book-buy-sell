package com.example.book_service.repository;

import com.example.book_service.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    @Test
    void findBookByName(){

        Book book =new Book();
        book.setBookName("Polity");
        book.setBookAuthor("Laxshmikant");
        book.setBookPrice(100.0);
        book.setBookStock(10);

        bookRepository.save(book);

        Optional<Book> foundBook=bookRepository.findByBookName("Polity");

        assertTrue(foundBook.isPresent());
        assertEquals("Polity",foundBook.get().getBookName());
        assertEquals("Laxshmikant",foundBook.get().getBookAuthor());
        assertEquals(100.0,foundBook.get().getBookPrice());
        assertEquals(10,foundBook.get().getBookStock());

    }

}