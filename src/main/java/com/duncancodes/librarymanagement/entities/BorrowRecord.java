package com.duncancodes.librarymanagement.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.HashMap;
import java.util.List;

@Entity
public class BorrowRecord {

	@Id
	private int id;

	private HashMap<User, List<Book>> borrowedBooks = new HashMap<>();

	public BorrowRecord(HashMap<User, List<Book>> borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}

	public HashMap<User, List<Book>> getBorrowedBooks() {
		return borrowedBooks;
	}

	public void setBorrowedBooks(HashMap<User, List<Book>> borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}
}
