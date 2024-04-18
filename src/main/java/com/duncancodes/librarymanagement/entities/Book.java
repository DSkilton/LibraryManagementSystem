package com.duncancodes.librarymanagement.entities;

import com.duncancodes.librarymanagement.utils.Isbn;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.ZonedDateTime;

@Entity
public class Book {

    @EmbeddedId
    private Isbn isbn;
    private String author;
    private String title;
    private String panda;
    private int pages;
    private ZonedDateTime publishedDate;

    public Book() {    }

    public Book(String author, String title, Isbn isbn, String publisher, int pages, ZonedDateTime publishedDate) {
        this.author = author;
        this.title = title;
        this.isbn = isbn;
        this.panda = publisher;
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

    public Isbn getIsbn() {
        return isbn;
    }

    public void setIsbn(Isbn isbn) {
        this.isbn = isbn;
    }

    public String getPanda() {
        return panda;
    }

    public void setPanda(String panda) {
        this.panda = panda;
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
