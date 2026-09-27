package com.poonam.booklibrary.Controller;

import com.poonam.booklibrary.DTO.BookReqDTO;
import com.poonam.booklibrary.DTO.BookResDTO;
import com.poonam.booklibrary.Entity.BookEntity;
import com.poonam.booklibrary.Service.BookService;
import com.poonam.booklibrary.Service.BookServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

private final BookService bookService;

    public BookController(BookService bookService){
       this.bookService = bookService;
    }

    //=========================
    //Add books
    //=========================
    @PostMapping("/add")
    public ResponseEntity<?> addBook(@Valid @RequestBody BookReqDTO bookReqDTO){
        BookResDTO addedBook = bookService.addBook(bookReqDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedBook);
    }

    //=========================
    //Read all books
    //=========================
    @GetMapping("/all")
    public ResponseEntity<List<BookResDTO>> readAllBook(){

        List<BookEntity> savedBookList= bookService.getAllBooks();
        //khaali list banao
        List<BookResDTO> emptyDtoList = new ArrayList<>();
        for(BookEntity book : savedBookList){
            BookResDTO bookResDTO = BookResDTO.builder()
                    .bookId(book.getBookId())
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .price(book.getPrice())
                    .isAvailable(book.getIsAvailable())
                    .build();
            //ar DTO ko list me daalo
            emptyDtoList.add(bookResDTO);
        }
        return ResponseEntity.ok(emptyDtoList);
    }

    //=========================
    //Read specific one book
    //=========================
    @GetMapping("/{bookId}")
    public ResponseEntity<?> getOneBook(@PathVariable Long bookId){
     BookResDTO bookResDTO = bookService.getOneBook(bookId);

     if(bookResDTO == null){
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found with id : " + bookId);
     }
     return ResponseEntity.ok(bookResDTO);
    }

    //=========================
    //Update book
    //=========================

    @PutMapping("/update/{bookId}")
    public ResponseEntity<?>updateBook(@PathVariable Long bookId, @Valid @RequestBody BookReqDTO bookReqDTO){
        BookResDTO bookResDTO = bookService.updateBook(bookId, bookReqDTO);

        if(bookResDTO == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Updated book " + bookId);
        }
        return ResponseEntity.ok(bookResDTO);
    }

    //=========================
    //Hard Delete book by bookId
    //=========================

    @DeleteMapping("/hardDelete/{bookId}")
    public ResponseEntity<?> hardDeleteBook(@PathVariable Long bookId){
       boolean isDeleted = bookService.hardDeleteBook(bookId);

        if(!isDeleted){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found with id " + bookId);
        }
        return ResponseEntity.ok("Book deleted successfully with id " + bookId);
    }

    //=========================
    //Soft Delete book by bookId
    //=========================
    @PutMapping("/softDelete/{bookId}")
    public ResponseEntity<?> softDeleteBook(@PathVariable Long bookId){
        boolean isSoftDeleted = bookService.softDeletedBook(bookId);

        if(!isSoftDeleted){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found with id "+bookId );
        }
        return ResponseEntity.ok("Book soft deleted successfully with id "+ bookId);
    }

    //=========================
    //Search book by author
    //=========================
    @GetMapping("/search")
    public ResponseEntity<List<BookResDTO>> searchByAuthro(@RequestParam String author){
      List<BookResDTO> searchResult = bookService.searchByAuthor(author);
      return ResponseEntity.ok(searchResult);
    }
}
