package com.duncancodes.librarymanagement.returning;

import com.duncancodes.librarymanagement.entities.Book;

public interface LateReturnService {
	double calculateLateFee(Integer daysLate);
}
