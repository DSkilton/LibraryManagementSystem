package com.duncancodes.librarymanagement.controllers;

import com.duncancodes.librarymanagement.entities.User;
import com.duncancodes.librarymanagement.exceptions.RecordNotFoundException;
import com.duncancodes.librarymanagement.services.UserService;
import com.duncancodes.librarymanagement.utils.Address;
import com.duncancodes.librarymanagement.utils.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.rmi.ServerException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserControllerTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	private User user;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		user = new User("Mr", "First", "Last", new Address(), new Contact());
	}

	@Test
	public void testCreateUser() throws ServerException {
		HttpServletRequest request = null; // Replace with a proper mock or a real request if necessary
		when(userService.saveUser(any(User.class))).thenReturn(user);
		ResponseEntity<User> response = userController.create(user, request);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	public void testGetUserById() throws RecordNotFoundException {
		when(userService.findById(any(Long.class))).thenReturn(Optional.of(user));
		ResponseEntity<User> response = userController.getById(1L);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(user.getId(), response.getBody().getId());
	}

	@Test
	public void testUpdateUser() {
		when(userService.findById(any(Long.class))).thenReturn(Optional.of(user));
		when(userService.updateUser(any(User.class))).thenReturn(user);
		ResponseEntity<Object> response = userController.updateUser(user);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void testUpdateUserNotFound() {
		when(userService.findById(any(Long.class))).thenReturn(Optional.empty());
		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
			userController.updateUser(user);
		});
		assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
	}
}
