package com.tahera.controller;

import com.tahera.model.Book;
import com.tahera.model.dto.BookDTO;
import com.tahera.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "${v1APIBook}")
public class BookController {

    @Autowired
    BookService bookService;

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public void registerNewBook(@RequestBody Book book) {
        bookService.addNewBook(book);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/{id}")
    public void deleteByID(@PathVariable Long id) {
        bookService.deleteByID(id);
    }

    @CrossOrigin(origins = "*")
    @PutMapping
    public void update(@RequestBody Book book) {
        bookService.update(book);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/setBookRead")
    public ResponseEntity setBookRead(@RequestBody Book book){
        boolean markedAsRead=bookService.setBookRead(book);
        if(markedAsRead){
            return ResponseEntity.ok(markedAsRead);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
