package com.poonam.booklibrary.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookReqDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @Positive(message = "Price must be greater than 0")
    private Double price;
}


/**
 * Kyun @AllArgsConstructor/@NoArgsConstructor yaha nahi:
 * Ye sirf serialize hoga (Object → JSON, outgoing),
 * Jackson ko sirf getters chahiye honge
 * — koi constructor zaroori nahi
 */
