package com.duncancodes.librarymanagement.controllers;

import com.duncancodes.librarymanagement.services.BorrowService;
import com.duncancodes.librarymanagement.utils.Isbn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BorrowControllerTest {

	@Mock
	private BorrowService borrowService;

	@InjectMocks
	private BorrowController borrowController;

	private Isbn isbn;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		isbn = new Isbn();
		isbn.setIsbnCode(9781234567897L);
	}

	@Test
	public void testBorrowBook() {
		when(borrowService.borrowBook(1L, isbn)).thenReturn("Success");
		ResponseEntity<String> response = borrowController.borrowBook(1L, isbn);
		assertEquals(200, response.getStatusCodeValue());
		assertEquals("Book borrowed successfully", response.getBody());
	}
}
