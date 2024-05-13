package com.duncancodes.librarymanagement.controllers;

import com.duncancodes.librarymanagement.entities.Book;
import com.duncancodes.librarymanagement.services.BookService;
import com.duncancodes.librarymanagement.services.managers.BookManager;
import com.duncancodes.librarymanagement.utils.Isbn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookManager bookManager;

	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> allBooks = bookManager.getAllBooks();
		return ResponseEntity.ok(allBooks);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookByIsbn(@PathVariable Isbn isbn) {
		Optional<Book> optionalBook = bookManager.findByIsbn(isbn);
		return optionalBook.map(book -> ResponseEntity.ok(book)).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/addBook")
	public ResponseEntity<Book> saveBook(@RequestBody Book book) {
		Book savedBook = bookManager.save(book);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
	}

	@PutMapping("/updateBook")
	public ResponseEntity<Book> updatedBook(@RequestBody Book book) {
		Book updatedBook = bookManager.updateBook(book);

		if (updatedBook != null) {
			return ResponseEntity.ok(updatedBook);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteBook(@PathVariable Isbn isbn, @RequestParam(defaultValue = "false") boolean hardDelete) {
		if (hardDelete) {
			bookManager.hardDelete(isbn);
		} else {
			bookManager.softDelete(isbn);
		}
		return ResponseEntity.ok().build();
	}


}
