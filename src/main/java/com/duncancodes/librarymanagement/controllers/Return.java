package com.duncancodes.librarymanagement.controllers;

import com.duncancodes.librarymanagement.exceptions.RecordNotFoundException;
import com.duncancodes.librarymanagement.services.BorrowService;
import com.duncancodes.librarymanagement.utils.Isbn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/return")
public class Return {

	@Autowired
	private BorrowService borrowService;

	public ResponseEntity<String> returnBook(@RequestParam Long userId, @RequestParam Isbn bookId) throws RecordNotFoundException {
		String result = borrowService.returnBook(userId, bookId);
		if (result.equals("Success")) {
			return ResponseEntity.ok("Book returned successfully");
		} else {
			return ResponseEntity.badRequest().body(result);
		}

	}
}
