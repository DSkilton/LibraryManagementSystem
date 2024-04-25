package com.duncancodes.librarymanagement.borrowing;

import com.duncancodes.librarymanagement.entities.User;

public interface BorrowingStratergy {
	boolean checkIfEligible(User user);
}
