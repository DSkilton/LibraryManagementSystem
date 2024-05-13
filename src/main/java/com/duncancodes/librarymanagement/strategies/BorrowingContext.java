package com.duncancodes.librarymanagement.strategies;

import com.duncancodes.librarymanagement.entities.User;

public class BorrowingContext {
	private BorrowingStratergy stratergy;

	public BorrowingContext(BorrowingStratergy stratergy) {
		this.stratergy = stratergy;
	}

	public boolean executeStrategery(User user) {
		return stratergy.checkIfEligible(user);
	}
}
