package com.duncancodes.librarymanagement.entities;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Entity
public class BorrowRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;

	private ZonedDateTime borrowDate;
	private ZonedDateTime dueDate;
	private ZonedDateTime returnDate;
	private Boolean isActive;
	private String status;

	public BorrowRecord() {
		this.isActive = true;
		this.status = "BORROWED";
	}

	public BorrowRecord(User user, Book book, ZonedDateTime borrowDate, ZonedDateTime dueDate) {
		this.user = user;
		this.book = book;
		this.borrowDate = borrowDate;
		this.dueDate = dueDate;
		this.isActive = true;
		this.status = "BORROWED";
	}

	public BorrowRecord(User user, Book book, boolean b, ZonedDateTime now, ZonedDateTime dueDate) {
		this.user = user;
		this.book = book;
		this.borrowDate = now;
		this.dueDate = dueDate;
	}

	// Getters and setters
	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public ZonedDateTime getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(ZonedDateTime borrowDate) {
		this.borrowDate = borrowDate;
	}

	public ZonedDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(ZonedDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public ZonedDateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(ZonedDateTime returnDate) {
		this.returnDate = returnDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// Additional methods
	public boolean isOverdue() {
		return ZonedDateTime.now().isAfter(this.dueDate);
	}

	public double calculateFine() {
		if (this.returnDate != null && this.returnDate.isAfter(this.dueDate)) {
			long daysLate = ZonedDateTime.now().until(this.returnDate, ChronoUnit.DAYS);
			return daysLate * 1.5; // Example fine calculation
		}
		return 0.0;
	}
}
