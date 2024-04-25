package com.duncancodes.librarymanagement.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BorrowRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;

	@OneToMany
	private List<Book> books;

	public BorrowRecord(User user, List<Book> books) {
		this.user = user;
		this.books = books;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Book> getBooks(Long userId) {
		return books == null ? new ArrayList<>() : books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
