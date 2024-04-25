package com.duncancodes.librarymanagement.Borrowing;

import com.duncancodes.librarymanagement.entities.BorrowRecord;
import com.duncancodes.librarymanagement.entities.User;
import com.duncancodes.librarymanagement.services.BookService;
import com.duncancodes.librarymanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentBorrowing implements BorrowingStratergy {

	@Autowired
	private BorrowRecord borrowRecord;

	@Override
	public boolean checkIfEligible(User user) {
		return borrowRecord.getBooks(user.getId()).size() < 3;
	}
}
