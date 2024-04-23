package com.duncancodes.librarymanagement.entities;

import com.duncancodes.librarymanagement.utils.Isbn;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.time.ZonedDateTime;

@Entity
public class Book {

	@EmbeddedId
	private Isbn isbn;
	private String author;
	private String title;
	private int pages;
	private ZonedDateTime publishedDate;
	private Boolean isBorrowed;

	public Book() {	}

	public Book(String author, String title, Isbn isbn, int pages, ZonedDateTime publishedDate, Boolean isBorrowed) {
		this.author = author;
		this.title = title;
		this.isbn = isbn;
		this.pages = pages;
		this.publishedDate = publishedDate;
		this.isBorrowed = isBorrowed;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Isbn getIsbn() {
		return isbn;
	}

	public void setIsbn(Isbn isbn) {
		this.isbn = isbn;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public ZonedDateTime getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(ZonedDateTime publishedDate) {
		this.publishedDate = publishedDate;
	}

	public Boolean getBorrowed() {
		return isBorrowed;
	}

	public void setBorrowed(Boolean borrowed) {
		isBorrowed = borrowed;
	}

	@Override
	public String toString() {
		return "Book{"
			+ "isbn=" + isbn
			+ ", author='" + author + '\''
			+ ", title='" + title + '\''
			+ ", pages=" + pages
			+ ", publishedDate=" + publishedDate
			+ ", isBorrowed=" + isBorrowed
			+ '}';
	}
}
