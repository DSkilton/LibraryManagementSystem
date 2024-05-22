package com.duncancodes.librarymanagement.fines;

public class AdditionalFine extends FineDecorator {
	public AdditionalFine(LateReturnService decoratedFine) {
		super(decoratedFine);
	}

	@Override
	public double calculateLateFee(Integer daysLate) {
		return super.calculateLateFee(daysLate) + 2.00;
	}
}
