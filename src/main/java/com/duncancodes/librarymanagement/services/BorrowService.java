package com.duncancodes.librarymanagement.services;

import com.duncancodes.librarymanagement.entities.Book;
import com.duncancodes.librarymanagement.entities.BorrowRecord;
import com.duncancodes.librarymanagement.entities.User;
import com.duncancodes.librarymanagement.exceptions.RecordNotFoundException;
import com.duncancodes.librarymanagement.fines.HalfTermFine;
import com.duncancodes.librarymanagement.fines.LateReturnService;
import com.duncancodes.librarymanagement.notifications.Notification;
import com.duncancodes.librarymanagement.notifications.NotificationFactory;
import com.duncancodes.librarymanagement.notifications.NotificationType;
import com.duncancodes.librarymanagement.repositories.BorrowRepository;
import com.duncancodes.librarymanagement.utils.Isbn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BorrowService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BorrowService.class);

	@Autowired
	private BookService bookService;

	@Autowired
	private UserService userService;

	@Autowired
	private BorrowRepository borrowRepository;

	public List<Book> getBorrowedBooks(Long userId) {
		return borrowRepository.findUsersBorrowedBooksByUserId(userId, true);
	}

	public String returnBook(Long userId, Isbn isbn) throws RecordNotFoundException {
		if (userId < 0) {
			throw new RecordNotFoundException("UserId not found", userId);
		}

		if (isbn.getIsbnCode() < 0) {
			throw new RecordNotFoundException("BookId not found", isbn.getIsbnCode());
		}

		Optional<User> optionalUser = userService.findById(userId);
		if (!optionalUser.isPresent()) {
			throw new RecordNotFoundException("UserId not found", userId);
		}
		User user = optionalUser.get();

		Optional<Book> optionalBook = bookService.findByIsbn(isbn);
		if (!optionalBook.isPresent()) {
			throw new RecordNotFoundException("Book not found", isbn.getIsbnCode());
		}

		Book book = optionalBook.get();
		List<Book> borrowedBooks = borrowRepository.findUsersBorrowedBooksByUserId(userId, true);
		if (!borrowedBooks.contains(book)) {
			return "UserId: {}, has not borrowed Book: {}" + userId + book;
		}

		Optional<BorrowRecord> optionalBorrowRecord = borrowRepository.findUsersBorrowedBook(user, book);
		if (!optionalBorrowRecord.isPresent()) {
			return "BorrowRecord not found for UserId: " + user.getId();
		}
		BorrowRecord borrowRecord = optionalBorrowRecord.get();
		book.setBorrowed(false);
		borrowRecord.setBook(book);
		borrowRepository.save(borrowRecord);

		long daysLate = ChronoUnit.DAYS.between(borrowRecord.getDueDate(), ZonedDateTime.now());
		if (daysLate > 0) {
			LateReturnService lateReturnService = new HalfTermFine(); // Or any other strategy based on the context
			double lateFee = lateReturnService.calculateLateFee((int) daysLate);
			// Notify user about the late fee
			Notification notification = NotificationFactory.createNotification(NotificationType.EMAIL);
			notification.sendNotification("You have a late fee of " + lateFee + " for returning the book late.");

		}
		return "Success";
	}

	private Book getBook(Isbn isbn) {
		return bookService.getBookByIsbn(isbn).orElseThrow();
	}

	public String borrowBook(Long userId, Isbn isbn) {
		Optional<User> optionalUser = userService.findById(userId);
		Optional<Book> optionalBook = bookService.findByIsbn(isbn);

		if (optionalUser.isPresent() && optionalBook.isPresent()) {
			User user = optionalUser.get();
			Book book = optionalBook.get();
			book.setBorrowed(true);

			BorrowRecord borrowRecord = new BorrowRecord(user, book, true, ZonedDateTime.now(), ZonedDateTime.now().plusDays(14));
			borrowRepository.save(borrowRecord);
			return "Success";
		}
		return "Unsuccessful";
	}
}
