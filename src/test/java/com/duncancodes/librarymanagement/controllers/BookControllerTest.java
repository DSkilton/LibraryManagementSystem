package com.duncancodes.librarymanagement.controllers;

import com.duncancodes.librarymanagement.entities.Book;
import com.duncancodes.librarymanagement.services.managers.BookManager;
import com.duncancodes.librarymanagement.utils.Isbn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BookControllerTest {

	@Mock
	private BookManager bookManager;

	@InjectMocks
	private BookController bookController;

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
	public void testGetAllBooks() {
		when(bookManager.getAllBooks()).thenReturn(Collections.singletonList(book));
		ResponseEntity<List<Book>> response = bookController.getAllBooks();
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(1, response.getBody().size());
	}

	@Test
	public void testGetBookByIsbn() {
		when(bookManager.findByIsbn(isbn)).thenReturn(Optional.of(book));
		ResponseEntity<Book> response = bookController.getBookByIsbn(isbn);
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(book.getIsbn(), response.getBody().getIsbn());
	}

	@Test
	public void testSaveBook() {
		when(bookManager.save(book)).thenReturn(book);
		ResponseEntity<Book> response = bookController.saveBook(book);
		assertEquals(201, response.getStatusCodeValue());
		assertEquals(book.getIsbn(), response.getBody().getIsbn());
	}
}
