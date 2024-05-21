package com.duncancodes.librarymanagement.controllers;

import com.duncancodes.librarymanagement.services.BorrowService;
import com.duncancodes.librarymanagement.utils.Isbn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/borrow")
public class BorrowController {

	@Autowired
	private BorrowService borrowService;

	@PostMapping("/book")
	public ResponseEntity<String> borrowBook(Long userId, Isbn isbn) {
		String result = borrowService.borrowBook(userId, isbn);
		if ("Success".equals(result)) {
				return ResponseEntity.ok("Book borrowed successfully");
			}
		return ResponseEntity.badRequest().body(result);
	}

	

}
