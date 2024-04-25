package com.duncancodes.librarymanagement.repositories;

import com.duncancodes.librarymanagement.entities.Book;
import com.duncancodes.librarymanagement.utils.Isbn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Isbn> {
	List<Book> findByAuthor(String author);

	List<Book> findByIsBorrowed(boolean isBorrowed);

	@Query("SELECT br FROM BorrowRecord br WHERE br.user.id =?1")
	List<Book> findUsersBorrowedBooks(Long userId);


}
