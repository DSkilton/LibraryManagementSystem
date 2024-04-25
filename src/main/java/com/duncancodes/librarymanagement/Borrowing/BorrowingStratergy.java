package com.duncancodes.librarymanagement.Borrowing;

import com.duncancodes.librarymanagement.entities.User;

public interface BorrowingStratergy {
	boolean checkIfEligible(User user);
}
