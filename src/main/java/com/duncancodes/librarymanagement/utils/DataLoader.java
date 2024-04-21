package com.duncancodes.librarymanagement.utils;

import com.duncancodes.librarymanagement.entities.Book;
import com.duncancodes.librarymanagement.entities.User;
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
		for (int i = 0; i < amount; i++) {
			String title = UserGenerator.titles[ThreadLocalRandom.current().nextInt(6)];
			String firstName = UserGenerator.firstNames[ThreadLocalRandom.current().nextInt(50)];
			String lastName = UserGenerator.lastNames[ThreadLocalRandom.current().nextInt(50)];

			Address address = new Address();
			address.setAddress1(String.valueOf(ThreadLocalRandom.current().nextInt(999)));
			address.setAddress2(UserGenerator.streetNames[ThreadLocalRandom.current().nextInt(74)]);
			address.setCounty(UserGenerator.englandCounties[ThreadLocalRandom.current().nextInt(48)]);
			address.setPostcode(generatePostcode(address));

			Contact contact = new Contact();
			contact.setEmail(generateEmail(firstName, lastName));
			contact.setMobile(generateMobile());

			User user = new User(title, firstName, lastName, address, contact);
			userRepository.save(user);
		}
	}

	private static String generateEmail(String firstName, String lastName) {
		StringBuilder sb = new StringBuilder();
		sb.append(firstName.charAt(0)).append(lastName).append("@").append(UserGenerator.emailDomains[ThreadLocalRandom.current().nextInt(20)]);
		return sb.toString();
	}

	private static String generateMobile() {
		StringBuilder sb = new StringBuilder("07");
		for (int i = 0; i < 9; i++) {
			sb.append(ThreadLocalRandom.current().nextInt(9));
		}
		return sb.toString();
	}

	private static String generatePostcode(Address address) {
		StringBuilder sb = new StringBuilder();
		sb.append(Character.toUpperCase(address.getCounty().charAt(0)));
		sb.append(Character.toUpperCase(address.getCounty().charAt(1)));
		sb.append(String.format("%02d", ThreadLocalRandom.current().nextInt(100)));
		sb.append(" ");

		sb.append(ThreadLocalRandom.current().nextInt(9));
		sb.append(Character.toUpperCase(address.getCounty().charAt(address.getCounty().length() - 2)));
		sb.append(Character.toUpperCase(address.getCounty().charAt(address.getCounty().length() - 1)));
		return sb.toString();
	}
}
