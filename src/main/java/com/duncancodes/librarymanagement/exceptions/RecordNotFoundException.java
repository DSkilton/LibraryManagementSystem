package com.duncancodes.librarymanagement.exceptions;

import com.duncancodes.librarymanagement.entities.Book;

public class RecordNotFoundException extends Exception {
	public RecordNotFoundException(String s, Long id) {

	}

	public RecordNotFoundException(String bookNotFound, Book book) {
	}
}
