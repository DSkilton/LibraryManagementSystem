package com.duncancodes.librarymanagement.repositories;

import com.duncancodes.librarymanagement.entities.Book;
import com.duncancodes.librarymanagement.entities.BorrowRecord;
import com.duncancodes.librarymanagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRepository extends JpaRepository<BorrowRecord, Long> {

	@Query("SELECT br.book FROM BorrowRecord br WHERE br.user.id = ?1 AND br.isActive = ?2")
	List<Book> findUsersBorrowedBooksByUserId(Long userId, Boolean isActive);

	@Query("SELECT br FROM BorrowRecord br WHERE br.user = ?1 AND br.book = ?2")
	Optional<BorrowRecord> findByUserAndBook(User user, Book book);
}
