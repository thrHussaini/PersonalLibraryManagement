package com.tahera.service;

import com.tahera.model.Book;
import com.tahera.model.dto.BookDTO;
import com.tahera.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks
    BookService bookService;
    @Mock
    BookRepository bookRepository;

    @Test
    public void shouldReturnAllAvaialbleBooks() {
        Book book1 = new Book();
        book1.setAuthor("author");
        Mockito.when(bookRepository.findAll()).thenReturn(List.of(book1));
        List<BookDTO> allBooks = bookService.getAllBooks();
        Assertions.assertEquals(book1.getAuthor(), allBooks.get(0).getAuthor());
    }

    @Test
    public void shouldAddNewBook() {
        Book book = new Book(1L, "test", 1, true, "test");
        bookService.addNewBook(book);
        Mockito.verify(bookRepository).save(book);
    }

    @Test
    public void shouldDeleteBookByID() {
        Long id = 1L;
        bookService.deleteByID(id);
        Mockito.verify(bookRepository).deleteById(id);
    }

    @Captor
    ArgumentCaptor<Book> bookCaptor;

    @Test
    public void shouldUpdateBook() {
        Book book = new Book(1L, "test", 1, true, "test");
        Mockito.when(bookRepository.findByTitleAndAuthor(book.getTitle(), book.getAuthor())).thenReturn(Optional.of(book));
        Book updateBook = new Book();
        updateBook.setAuthor("test");
        updateBook.setTitle("test");
        updateBook.setRate(2);
        updateBook.setRead(false);

       // bookService.update(updateBook);

        Mockito.verify(bookRepository).save(bookCaptor.capture());
        Book persistedBook = bookCaptor.getValue();
        Assertions.assertEquals(book.getId(), persistedBook.getId());
        Assertions.assertEquals(book.getRate(), persistedBook.getRate());
        Assertions.assertEquals(book.getIsRead(), persistedBook.getIsRead());
    }

    @Test
    public void shouldNotHaveUpdateBookWhenItDoesnotExists() {
        Book book = new Book(1L, "test", 1, true, "test");
        Mockito.when(bookRepository.findByTitleAndAuthor(book.getTitle(), book.getAuthor())).thenReturn(Optional.empty());

        bookService.update(book);

        Mockito.verify(bookRepository, Mockito.never()).save(any());
    }

    @Test
    public void shouldSetBookAsRead() {
        Book book = new Book(1L, "test", 1, false, "test");
        Mockito.when(bookRepository.findByTitleAndAuthor(book.getTitle(), book.getAuthor())).thenReturn(Optional.of(book));

        bookService.setBookRead(book);

        Mockito.verify(bookRepository).save(bookCaptor.capture());
        Book persistedBook = bookCaptor.getValue();
        Assertions.assertTrue(persistedBook.getIsRead());
    }

    @Test
    public void shouldNotHaveSetBookWhenItDoesnotExists() {
        Book book = new Book(1L, "test", 1, false, "test");
        Mockito.when(bookRepository.findByTitleAndAuthor(book.getTitle(), book.getAuthor())).thenReturn(Optional.empty());

        bookService.setBookRead(book);

        Mockito.verify(bookRepository, Mockito.never()).save(any());
    }
}