package com.poonam.booklibrary.Service;

import com.poonam.booklibrary.DTO.BookReqDTO;
import com.poonam.booklibrary.DTO.BookResDTO;
import com.poonam.booklibrary.Entity.BookEntity;
import com.poonam.booklibrary.Repository.BookRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    // Constructor Injection
    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }


     //=========================
    //Add books
    //=========================
    @Override
   public BookResDTO addBook(BookReqDTO bookReqDTO){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(bookReqDTO.getTitle());
        bookEntity.setAuthor(bookReqDTO.getAuthor());
        bookEntity.setPrice(bookReqDTO.getPrice());

        BookEntity saveBook = bookRepository.save(bookEntity);
        return BookResDTO.builder()
                .bookId(saveBook.getBookId())
                .title(saveBook.getTitle())
                .author(saveBook.getAuthor())
                .price(saveBook.getPrice())
                .isAvailable(saveBook.getIsAvailable())
                .build();
    }

    //=========================
    //Read all books
    //=========================
    @Override
    public List<BookEntity> getAllBooks() {
        return bookRepository.findByIsDeletedFalse();
    }



    //=========================
    //Read specific one book
    //=========================
    @Override
    public BookResDTO getOneBook(Long bookId) {
        Optional<BookEntity> boxOptional = bookRepository.findById(bookId);

        if(boxOptional.isEmpty()){
            return null;
        }
        BookEntity bookEntity = boxOptional.get();
        return BookResDTO.builder()
                .bookId(bookEntity.getBookId())
                .title(bookEntity.getTitle())
                .author(bookEntity.getAuthor())
                .price(bookEntity.getPrice())
                .isAvailable(bookEntity.getIsAvailable())
                .build();

    }

    //=========================
    //Update book by Id
    //=========================
    @Override
    public BookResDTO updateBook(Long bookId, BookReqDTO bookReqDTO) {
        Optional<BookEntity> boxOptional = bookRepository.findById(bookId);
        if(boxOptional.isEmpty())
             return null;

        //existing entity nikalo
        BookEntity oldEntity = boxOptional.get();

        oldEntity.setTitle(bookReqDTO.getTitle());
        oldEntity.setAuthor(bookReqDTO.getAuthor());
        oldEntity.setPrice(bookReqDTO.getPrice());

        //isi ko save karo
        BookEntity updatedEntity = bookRepository.save(oldEntity);

        return BookResDTO.builder()
                .bookId(updatedEntity.getBookId())
                .title(updatedEntity.getTitle())
                .author(updatedEntity.getAuthor())
                .price(updatedEntity.getPrice())
                .isAvailable(updatedEntity.getIsAvailable())
                .build();
    }

    //=========================
    //Hard delete book by id
    //=========================
    @Override
    public boolean hardDeleteBook(Long bookId) {
        Optional<BookEntity> boxOptional = bookRepository.findById(bookId);

        if(boxOptional.isEmpty())
        return false;

        BookEntity oldEntity = boxOptional.get();
       bookRepository.delete(oldEntity);

        return true;
    }

    //=========================
    //soft delete book by id
    //=========================
    @Override
    public boolean softDeletedBook(Long bookId) {
        Optional<BookEntity> boxOptional = bookRepository.findById(bookId);

        if(boxOptional.isEmpty())
           return false;

        BookEntity oldEntity = boxOptional.get();

        // variable store karne ki zaroorat nahi
        oldEntity.setIsDeleted(true);
         bookRepository.save(oldEntity);
        return true;
    }

    //=========================
   //Search book by author
  //=========================
    @Override
    public List<BookResDTO> searchByAuthor(String author) {

        List<BookEntity> bookList = bookRepository.findByAuthor(author);

        List<BookResDTO> dtoList = new ArrayList<>();

        for(BookEntity book : bookList){
            BookResDTO bookResDTO = BookResDTO.builder()
                    .bookId(book.getBookId())
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .price(book.getPrice())
                    .isAvailable(book.getIsAvailable())
                    .build();

            dtoList.add(bookResDTO);

        }
                return dtoList;
    }
}
