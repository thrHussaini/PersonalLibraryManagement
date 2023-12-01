package com.tahera.service;

import com.tahera.model.Book;
import com.tahera.model.dto.BookDTO;
import com.tahera.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> getAllBooks() {
        List<Book> allBooks = bookRepository.findAll();
        return allBooks.stream().map(this::convertBookDTO).collect(Collectors.toList());
    }

    public void addNewBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteByID(Long id) {
        bookRepository.deleteById(id);
    }

    public void update(Book book) {
        Optional<Book> optionalBook = bookRepository.findByTitleAndAuthor(book.getTitle(), book.getAuthor());
        if (optionalBook.isPresent()) {
            Book updatedBook = optionalBook.get();
            updatedBook.setRead(book.getIsRead());
            updatedBook.setRate(book.getRate());
            bookRepository.save(updatedBook);
        }
    }
    private BookDTO convertBookDTO(Book book) {
        return new BookDTO(book.getId(), book.getTitle(), book.getRate(), book.getIsRead(), book.getAuthor());
    }
}
