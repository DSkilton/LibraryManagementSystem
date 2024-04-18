package com.duncancodes.librarymanagement.utils;

import com.duncancodes.librarymanagement.entities.Book;
import com.duncancodes.librarymanagement.enums.CountryCode;
import com.duncancodes.librarymanagement.enums.Publisher;
import com.duncancodes.librarymanagement.repositories.BookRepository;
import com.duncancodes.librarymanagement.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class DataLoader implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        loadBooks(100);
        loadUsers(100);
    }

    private void loadBooks(int amount) {
        for (int i = 0; i < amount; i++) {
            int rand = ThreadLocalRandom.current().nextInt(50);
            String author = BookGenerator.authors[rand];
            String title = BookGenerator.titles[rand];
            Isbn isbn = new Isbn(CountryCode.getRandomCountryCode(), Publisher.OXFORD_UNIVERSITY_PRESS, 3, 4);

            String publisher = BookGenerator.publishers[rand];
            int pages = ThreadLocalRandom.current().nextInt(500)+100;
            ZonedDateTime publishedDate = ZonedDateTime.now().minusDays(ThreadLocalRandom.current().nextInt(365 * 10));
            Book book = new Book(author, title, isbn, publisher, pages, publishedDate);

            bookRepository.save(book);
        }
    }

    private void loadUsers(int amount) {

    }
}
