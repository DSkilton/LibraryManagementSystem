package com.duncancodes.librarymanagement.strategies;

import com.duncancodes.librarymanagement.entities.User;

public interface BorrowingStratergy {
	boolean checkIfEligible(User user);
}
