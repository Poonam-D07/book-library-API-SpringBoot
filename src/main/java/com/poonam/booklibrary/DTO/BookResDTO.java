package com.poonam.booklibrary.DTO;

import lombok.*;

@Getter
@Setter
@Builder
public class BookResDTO {
    private Long bookId;
    private String title;
    private String author;
    private Double price;
    private Boolean isAvailable;

}
