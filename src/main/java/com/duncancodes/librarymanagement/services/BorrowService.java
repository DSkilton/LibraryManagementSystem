package com.duncancodes.librarymanagement.services;

import com.duncancodes.librarymanagement.entities.Book;
import com.duncancodes.librarymanagement.entities.BorrowRecord;
import com.duncancodes.librarymanagement.entities.User;
import com.duncancodes.librarymanagement.repositories.BookRepository;
import com.duncancodes.librarymanagement.repositories.BorrowRepository;
import com.duncancodes.librarymanagement.utils.Isbn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BorrowService.class);

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BorrowRepository borrowRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private BookService bookService;

	public List<Book> getBorrowedBooks(Long userId) {
		return bookRepository.findUsersBorrowedBooks(userId);
	}

	public void removeBookFromBorrowRecord(BorrowRecord borrowRecord) {
		List<Book> borrowedBooks = bookRepository.findUsersBorrowedBooks(borrowRecord.getUser().getId());
	}

	public String borrowBook(Long userId, Isbn isbn) {
		Optional<User> optionalUser = userService.findById(userId);

		if (!optionalUser.isPresent()) {
			return "Unsuccessful";
		} else {
			User user = optionalUser.get();
			Optional<Book> optionalBook = bookService.findByIsbn(isbn);

			if (optionalBook.isPresent()) {
				Book book = optionalBook.get();
				book.setBorrowed(true);

				BorrowRecord borrowRecord = new BorrowRecord(user, book);
				BorrowRecord savedBorrowRecord = borrowRepo.save(borrowRecord);
				LOGGER.debug("Created BorrowRecord with Id: {} ", savedBorrowRecord.getId());
			}
		}
		return "Successful";
	}

	public String returnBook(Long userId, Long bookId) {
		return "";
	}
}
