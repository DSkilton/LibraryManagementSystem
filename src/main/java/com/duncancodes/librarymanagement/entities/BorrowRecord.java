package com.duncancodes.librarymanagement.entities;

import com.duncancodes.librarymanagement.repositories.BorrowRepository;

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

	private Boolean isBorrowed;

	public BorrowRecord(User user, Book book, Boolean isBorrowed) {
		this.user = user;
		this.book = book;
		this.isBorrowed = isBorrowed;
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

	public Book getBook(Long userId) {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}
