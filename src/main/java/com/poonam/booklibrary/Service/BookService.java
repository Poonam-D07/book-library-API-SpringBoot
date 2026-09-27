package com.poonam.booklibrary.Service;

import com.poonam.booklibrary.DTO.BookReqDTO;
import com.poonam.booklibrary.DTO.BookResDTO;
import com.poonam.booklibrary.Entity.BookEntity;

import java.util.List;

public interface BookService {
    //Create
    BookResDTO addBook(BookReqDTO bookReqDTO);

    //Read all books
    List<BookEntity> getAllBooks();

    //Read specific one books
     BookResDTO getOneBook(Long bookId);

    //Update book
    BookResDTO updateBook(Long bookId, BookReqDTO bookReqDTO);

    //delete book(hardcoded)
    boolean hardDeleteBook(Long bookId);

    //soft delete
    boolean softDeletedBook(Long bookId);

    //Search by Author
    List<BookResDTO> searchByAuthor(String author);
}
