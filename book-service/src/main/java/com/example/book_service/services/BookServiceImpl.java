package com.example.book_service.services;

import com.example.book_service.dto.BookResponse;
import com.example.book_service.entity.Book;
import com.example.book_service.exceptions.BookNotFound;
import com.example.book_service.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookServices{

    private final BookRepository repository;

    @Override
    public Page<Book> getAllBooks(int pageNo,
                                  int pageSize,
                                  String sortBy,
                                  String sortDir) {

        Sort sort=sortDir.equalsIgnoreCase("ASC")
                                ? Sort.by(sortBy).ascending()
                                : Sort.by(sortBy).descending();

        Pageable pageable= PageRequest.of(pageNo-1,pageSize,sort);
        return repository.findAll(pageable);
    }

    @Override
    public Book getBookById(Long id) {
        return repository.findById(id)
                .orElseThrow(()->new BookNotFound("Book Not Found:"+id));
    }

    @Override
    public void deleteBookById(Long id) {
        if(!repository.existsById(id)){
            throw new BookNotFound("Book Not Found: "+id);
        }
        repository.deleteById(id);
    }

    @Override
    public Book addBook(Book book) {
        return repository.save(book);
    }

    @Override
    public BookResponse getBookByBookName(String bookName) {
        Book book =repository.findByBookName(bookName)
                .orElseThrow(()->new BookNotFound("Book Not Found: "+bookName));
        return new BookResponse(
                book.getBookName(),
                book.getBookAuthor(),
                book.getBookPrice(),
                book.getBookStock()
        );
    }

    @Override
    @Transactional
    public BookResponse reduceBookQuantity(String bookName,
                                           int bookQuantity) {
        if(bookQuantity<=0){
            throw new BookNotFound("Book Quantity Should more Than One..");
        }
        Book book=repository.findByBookName(bookName)
                .orElseThrow(()->new BookNotFound("Book Not Found: "+bookName));

        if(book.getBookStock()<bookQuantity){
            throw new BookNotFound("Not Enough Book Stock.."+book.getBookStock());
        }

        book.setBookStock(book.getBookStock()-bookQuantity);
        Book reduced=repository.save(book);

        return new BookResponse(
                reduced.getBookName(),
                reduced.getBookAuthor(),
                reduced.getBookPrice(),
                reduced.getBookStock()
        );
    }
}
