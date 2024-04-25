package com.duncancodes.librarymanagement.returning;

import com.duncancodes.librarymanagement.utils.Constant;

public class BasicFine implements LateReturnService {

	@Override
	public double calculateLateFee(Integer daysLate) {
		return daysLate * Constant.REGULAR_FEE;
	}

}
