package com.example.order_service.feign;

import com.example.order_service.dto.BookResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "book-service",url = "http://localhost:8080")
public interface BookClient {

//    @GetMapping("/books/name/{bookName}")
//    BookResponse getBookByName(@PathVariable String bookName);

    @PutMapping("/books/reduceStock/{bookName}/{bookQuantity}")
    BookResponse reduceBookStock(@PathVariable String bookName,
                                 @PathVariable int bookQuantity);
}
