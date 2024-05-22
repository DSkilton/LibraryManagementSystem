package com.duncancodes.librarymanagement.strategies;

import com.duncancodes.librarymanagement.entities.User;

public class BorrowingContext {
	private BorrowingStrategy stratergy;

	public BorrowingContext(BorrowingStrategy stratergy) {
		this.stratergy = stratergy;
	}

	public boolean executeStrategery(User user) {
		return stratergy.checkIfEligible(user);
	}
}
