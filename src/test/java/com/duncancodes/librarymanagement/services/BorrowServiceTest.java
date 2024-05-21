package com.duncancodes.librarymanagement.services;

import com.duncancodes.librarymanagement.entities.Book;
import com.duncancodes.librarymanagement.entities.BorrowRecord;
import com.duncancodes.librarymanagement.entities.User;
import com.duncancodes.librarymanagement.exceptions.RecordNotFoundException;
import com.duncancodes.librarymanagement.repositories.BorrowRepository;
import com.duncancodes.librarymanagement.utils.Address;
import com.duncancodes.librarymanagement.utils.Contact;
import com.duncancodes.librarymanagement.utils.Isbn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BorrowServiceTest {

	@Mock
	private BookService bookService;

	@Mock
	private UserService userService;

	@Mock
	private BorrowRepository borrowRepository;

	@InjectMocks
	private BorrowService borrowService;

	private User user;
	private Book book;
	private BorrowRecord borrowRecord;
	private Isbn isbn;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		isbn = new Isbn();
		isbn.setIsbnCode(9781234567897L);
		user = new User("Mr", "First", "Last", new Address(), new Contact());
		book = new Book("Author", "Title", isbn, 300, ZonedDateTime.now(), false, true);
		borrowRecord = new BorrowRecord(user, book, ZonedDateTime.now(), ZonedDateTime.now().plusDays(14));
	}

	@Test
	public void testBorrowBookSuccess() {
		when(userService.findById(any(Long.class))).thenReturn(Optional.of(user));
		when(bookService.findByIsbn(any(Isbn.class))).thenReturn(Optional.of(book));
		when(borrowRepository.save(any(BorrowRecord.class))).thenReturn(borrowRecord);

		String result = borrowService.borrowBook(1L, isbn);
		assertEquals("Success", result);
	}

	@Test
	public void testReturnBookSuccess() throws RecordNotFoundException {
		when(userService.findById(any(Long.class))).thenReturn(Optional.of(user));
		when(bookService.findByIsbn(any(Isbn.class))).thenReturn(Optional.of(book));
		when(borrowRepository.findUsersBorrowedBook(any(User.class), any(Book.class))).thenReturn(Optional.of(borrowRecord));

		String result = borrowService.returnBook(1L, isbn);
		assertEquals("Success", result);
	}

	@Test
	public void testReturnBookUserNotFound() {
		when(userService.findById(any(Long.class))).thenReturn(Optional.empty());

		RecordNotFoundException exception = assertThrows(RecordNotFoundException.class, () -> {
			borrowService.returnBook(1L, isbn);
		});

		assertEquals("UserId not found", exception.getMessage());
	}
}
