package com.poonam.booklibrary.DTO;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookReqDTO {
//    private Long bookId;
    private String title;
    private String author;
    private Double price;
//    private Boolean isAvailable;
}


/**
 * Kyun @AllArgsConstructor/@NoArgsConstructor yaha nahi:
 * Ye sirf serialize hoga (Object → JSON, outgoing),
 * Jackson ko sirf getters chahiye honge
 * — koi constructor zaroori nahi
 */
