package com.duncancodes.librarymanagement.services;

import com.duncancodes.librarymanagement.entities.Book;
import com.duncancodes.librarymanagement.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepo;

	public Book saveBook(Book book) {
		return bookRepo.save(book);
	}

	public List<Book> getBooksByAuthor(String author) {
		return bookRepo.findByAuthor(author);
	}

	public List<Book> getAvailableBooks() {
		return bookRepo.findByIsBorrowed(false);
	}
}
