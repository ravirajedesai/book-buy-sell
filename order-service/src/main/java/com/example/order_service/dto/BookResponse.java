package com.example.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookResponse {
    private String bookName;
    private String bookAuthor;
    private Double bookPrice;
    private Integer bookStock;

}
