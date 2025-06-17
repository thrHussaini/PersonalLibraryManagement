package com.tahera.controller;

import com.tahera.model.Book;
import com.tahera.model.UserEntity;
import com.tahera.model.dto.BookDTO;
import com.tahera.security.JwtUtil;
import com.tahera.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.tahera.repository.UserRepository;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "${v1APIBook}")

public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;


    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyAuthority('Admin', 'Viewer')")
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public void registerNewBook(@RequestBody Book book) {
        bookService.addNewBook(book);
    }


    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping(value = "/{id}")
    public void deleteByID(@PathVariable Long id) {
        bookService.deleteByID(id);
    }


    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping
    public void update(@RequestBody Book book) {
        bookService.update(book);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/setBookRead")
    public ResponseEntity<?> setBookRead(@RequestBody Book book) {
        return bookService.setBookRead(book)
                ? ResponseEntity.ok(true)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntity loginUser) {
        return userRepository.findByUsername(loginUser.getUsername())
                .filter(user -> user.getPassword().equals(loginUser.getPassword()))
                .map(user -> {
                    String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
                    return ResponseEntity.ok(token);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"));
    }

}
