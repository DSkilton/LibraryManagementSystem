package com.duncancodes.librarymanagement.strategies;

import com.duncancodes.librarymanagement.entities.User;
import com.duncancodes.librarymanagement.services.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;

public class StaffBorrowing implements BorrowingStrategy {

	@Autowired
	private BorrowService borrowService;

	@Override
	public boolean checkIfEligible(User user) {
		return borrowService.getBorrowedBooks(user.getId()).size() < 5;
	}
}
