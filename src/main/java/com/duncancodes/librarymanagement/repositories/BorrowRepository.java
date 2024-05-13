package com.duncancodes.librarymanagement.repositories;

import com.duncancodes.librarymanagement.entities.Book;
import com.duncancodes.librarymanagement.entities.BorrowRecord;
import com.duncancodes.librarymanagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BorrowRepository extends JpaRepository<BorrowRecord, Long> {

	@Query("SELECT b FROM BorrowRecord WHERE b.userId = ?1 and b.isBorrowed = ?2")
	List<Book> findUsersBorrowedBooksByUserId(Long userId, Boolean isBorrowed);

	Optional<BorrowRecord> findUsersBorrowedBook(User user, Book book);

	@Query("SELECT b FROM BorrowRecord WHERE b.user = ?1 and b.book = ?2")
	Optional<BorrowRecord> findByUserIdAndBook(User user, Book book);
}
