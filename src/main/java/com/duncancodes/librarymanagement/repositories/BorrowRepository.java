package com.duncancodes.librarymanagement.repositories;

import com.duncancodes.librarymanagement.entities.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<BorrowRecord, Long> {
	
}
