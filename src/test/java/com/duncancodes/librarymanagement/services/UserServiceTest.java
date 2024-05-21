package com.duncancodes.librarymanagement.services;

import com.duncancodes.librarymanagement.entities.User;
import com.duncancodes.librarymanagement.repositories.UserRepository;
import com.duncancodes.librarymanagement.utils.Address;
import com.duncancodes.librarymanagement.utils.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceTest {

	@Mock
	private UserRepository userRepo;

	@InjectMocks
	private UserService userService;

	private User user;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		user = new User("Mr", "First", "Last", new Address(), new Contact());
	}

	@Test
	public void testSaveUser() {
		when(userRepo.save(any(User.class))).thenReturn(user);
		User savedUser = userService.saveUser(user);
		assertEquals(user.getId(), savedUser.getId());
	}

	@Test
	public void testFindById() {
		when(userRepo.findById(any(Long.class))).thenReturn(Optional.of(user));
		Optional<User> foundUser = userService.findById(1L);
		assertTrue(foundUser.isPresent());
		assertEquals(user.getId(), foundUser.get().getId());
	}
}
