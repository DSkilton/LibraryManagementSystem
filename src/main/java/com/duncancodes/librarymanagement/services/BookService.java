package com.duncancodes.librarymanagement.services;

import com.duncancodes.librarymanagement.entities.Book;
import com.duncancodes.librarymanagement.repositories.BookRepository;
import com.duncancodes.librarymanagement.utils.Isbn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepo;

	public Book save(Book book) {
		return bookRepo.save(book);
	}

	public List<Book> getBooksByAuthor(String author) {
		return bookRepo.findByAuthor(author);
	}

	public Optional<Book> getBookByIsbn(Isbn isbn) {
		return bookRepo.findByIsbn(isbn);
	}

	public List<Book> getAvailableBooks() {
		return bookRepo.findByIsBorrowed(false);
	}

	public List<Book> getAllBooks() {
		return bookRepo.findAll();
	}

	public Optional<Book> findByIsbn(Isbn isbn) {
		return bookRepo.findByIsbn(isbn);
	}

	@Transactional
	public Book updateBook(Book bookUpdate) {
		if (bookUpdate == null) {
			throw new IllegalArgumentException("Provided book cannot be null.");
		}

		Isbn isbn = bookUpdate.getIsbn();
		Optional<Book> optionalBook = bookRepo.findByIsbn(isbn);

		Book originalBook = optionalBook.orElseThrow(() ->
				new EntityNotFoundException("No book found with ISBN: " + isbn));

		if (!originalBook.getAuthor().equals(bookUpdate.getAuthor())) {
			originalBook.setAuthor(bookUpdate.getAuthor());
		}
		if (!originalBook.getTitle().equals(bookUpdate.getTitle())) {
			originalBook.setTitle(bookUpdate.getTitle());
		}
		if (originalBook.getBorrowed() != bookUpdate.getBorrowed()) {
			originalBook.setBorrowed(bookUpdate.getBorrowed());
		}
		if (originalBook.getPages() != bookUpdate.getPages()) {
			originalBook.setPages(bookUpdate.getPages());
		}
		if (!Objects.equals(originalBook.getPublishedDate(), bookUpdate.getPublishedDate())) {
			originalBook.setPublishedDate(bookUpdate.getPublishedDate());
		}

		return bookRepo.save(originalBook);
	}

	public Boolean hardDelete(Isbn isbn) {
		Optional<Book> optionalBook = bookRepo.findByIsbn(isbn);

		if (optionalBook.isPresent()) {
			Book book = optionalBook.get();
			bookRepo.delete(book);
			return true;
		}

		return false;
	}

	public Boolean softDelete(Isbn isbn) {
		Optional<Book> optionalBook = bookRepo.findByIsbn(isbn);

		if (optionalBook.isPresent()) {
			Book book = optionalBook.get();
			book.setIsActive(false);
			bookRepo.save(book);
			return true;
		}

		return false;
	}
}
