package com.duncancodes.librarymanagement.services;

import com.duncancodes.librarymanagement.entities.Book;
import com.duncancodes.librarymanagement.repositories.BookRepository;
import com.duncancodes.librarymanagement.utils.Isbn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BookServiceTest {

	@Mock
	private BookRepository bookRepo;

	@InjectMocks
	private BookService bookService;

	private Book book;
	private Isbn isbn;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		isbn = new Isbn();
		isbn.setIsbnCode(9781234567897L);
		book = new Book("Author", "Title", isbn, 300, ZonedDateTime.now(), false, true);
	}

	@Test
	public void testSaveBook() {
		when(bookRepo.save(any(Book.class))).thenReturn(book);
		Book savedBook = bookService.save(book);
		assertEquals(book.getIsbn(), savedBook.getIsbn());
	}

	@Test
	public void testFindByIsbn() {
		when(bookRepo.findByIsbn(isbn)).thenReturn(Optional.of(book));
		Optional<Book> foundBook = bookService.findByIsbn(isbn);
		assertTrue(foundBook.isPresent());
		assertEquals(book.getIsbn(), foundBook.get().getIsbn());
	}
}
