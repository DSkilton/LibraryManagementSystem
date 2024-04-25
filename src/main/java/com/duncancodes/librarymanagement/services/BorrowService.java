package com.duncancodes.librarymanagement.services;

import com.duncancodes.librarymanagement.entities.Book;
import com.duncancodes.librarymanagement.entities.BorrowRecord;
import com.duncancodes.librarymanagement.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowService {

	@Autowired
	private BookRepository bookRepository;

	public List<Book> getBorrowedBooks(Long userId) {
		return bookRepository.findUsersBorrowedBooks(userId);
	}

	public void removeBookFromBorrowRecord(BorrowRecord borrowRecord) {
		List<Book> borrowedBooks = bookRepository.findUsersBorrowedBooks(borrowRecord.getUser().getId());
	}
}
