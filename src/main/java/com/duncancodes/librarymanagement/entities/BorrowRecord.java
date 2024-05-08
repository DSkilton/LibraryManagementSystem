package com.duncancodes.librarymanagement.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BorrowRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "userid")
	private User user;

	@OneToOne
	private Book book;

	public BorrowRecord(User user, Book book) {
		this.user = user;
		this.book = book;
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBooks(Long userId) {
		return book;
	}

	public void setBooks(Book book) {
		this.book = book;
	}
}
