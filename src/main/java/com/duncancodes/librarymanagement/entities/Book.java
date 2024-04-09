package com.duncancodes.librarymanagement.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Entity
public class Book {

    @Id
    private String isbn;
    private String author;
    private String title;
    private String publisher;
    private int pages;
    private ZonedDateTime publishedDate;

    public Book(String author, String title, String isbn, String publisher, int pages, ZonedDateTime publishedDate) {
        this.author = author;
        this.title = title;
        this.isbn = isbn;
        this.publisher = publisher;
        this.pages = pages;
        this.publishedDate = publishedDate;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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
}
