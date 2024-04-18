package com.duncancodes.librarymanagement.repositories;

import com.duncancodes.librarymanagement.entities.Book;
import com.duncancodes.librarymanagement.utils.Isbn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Isbn> {
}
