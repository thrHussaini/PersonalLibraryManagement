package com.tahera.controller;

import com.tahera.model.Book;
import com.tahera.model.dto.BookDTO;
import com.tahera.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "${v1APIBook}")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping(value = "")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping(value = "")
    public void registerNewBook(@RequestBody Book book) {
        bookService.addNewBook(book);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteByID(@PathVariable Long id) {
        bookService.deleteByID(id);
    }

    @PutMapping(value = "")
    public void update(@RequestBody Book book) {
        //boolean isMatch= bookService.up
        bookService.update(book);
    }

}
