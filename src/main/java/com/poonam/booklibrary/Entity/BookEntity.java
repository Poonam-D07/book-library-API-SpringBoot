package com.poonam.booklibrary.Entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table (name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    private Double price;

    private Boolean isAvailable = true;

    // NEW — Soft Delete flag
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
}
