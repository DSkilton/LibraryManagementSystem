package com.duncancodes.librarymanagement.repositories;

import com.duncancodes.librarymanagement.entities.Book;
import com.duncancodes.librarymanagement.utils.Isbn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Isbn> {
	List<Book> findByAuthor(String author);

	List<Book> findByIsBorrowed(boolean isBorrowed);


}
