package com.duncancodes.librarymanagement.fines;

public class HalfTermFine extends BasicFine {

	public double calculateLateFee(Integer daysLate) {
		double baseFee = super.calculateLateFee(daysLate);
		return baseFee * 2;
	}
}
