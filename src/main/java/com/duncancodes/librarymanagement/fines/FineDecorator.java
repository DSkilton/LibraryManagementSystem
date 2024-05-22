package com.duncancodes.librarymanagement.fines;

public abstract class FineDecorator implements LateReturnService {
	protected LateReturnService decoratedFine;

	public FineDecorator(LateReturnService decoratedFine) {
		this.decoratedFine = decoratedFine;
	}

	public double calculateLateFee(Integer daysLate) {
		return decoratedFine.calculateLateFee(daysLate);
	}
}
