package com.poonam.booklibrary.Repository;

import com.poonam.booklibrary.Entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
//NEW
    List<BookEntity> findByIsDeletedFalse();

    //search author
    List<BookEntity> findByAuthor(String author);
}
