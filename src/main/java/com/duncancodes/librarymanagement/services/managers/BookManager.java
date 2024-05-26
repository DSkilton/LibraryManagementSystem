package com.duncancodes.librarymanagement.services.managers;

import com.duncancodes.librarymanagement.entities.Book;
import com.duncancodes.librarymanagement.services.BookService;
import com.duncancodes.librarymanagement.utils.Isbn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookManager {

	@Autowired
	private BookService bookService;

	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}

	public Optional<Book> findByIsbn(Isbn isbn) {
		return bookService.findByIsbn(isbn);
	}

	public Book save(Book book) {
		return bookService.save(book);
	}

	public Book updateBook(Book book) {
		return bookService.updateBook(book);
	}

	public Boolean hardDelete(Isbn isbn) {
		return bookService.hardDelete(isbn);
	}

	public Boolean softDelete(Isbn isbn) {
		return bookService.softDelete(isbn);
	}
}
