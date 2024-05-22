package com.duncancodes.librarymanagement.strategies;

import com.duncancodes.librarymanagement.entities.User;

public interface BorrowingStrategy {
	boolean checkIfEligible(User user);
}
