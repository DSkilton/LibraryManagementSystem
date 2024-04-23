package com.duncancodes.librarymanagement.controllers;

import com.duncancodes.librarymanagement.entities.User;
import com.duncancodes.librarymanagement.exceptions.RecordNotFoundException;
import com.duncancodes.librarymanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.rmi.ServerException;
import java.util.Optional;

import static com.duncancodes.librarymanagement.utils.Constant.USER_NOT_FOUND;
import static com.duncancodes.librarymanagement.utils.Constant.USER_NOT_FOUND_FOR_ID;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value="/addUser")
	public ResponseEntity<User> create(@RequestBody User newUser, HttpServletRequest request) throws ServerException {
		User user = userService.saveUser(newUser);

		if (user != null) {
			URI location = ServletUriComponentsBuilder.fromRequestUri(request)
					.path("/{id}")
					.buildAndExpand(user.getId())
					.toUri();
			return ResponseEntity.created(location).body(user);
		} else {
			throw new ServerException("Error in creating the User resource. Try again");
		}
	}

	@GetMapping(value="user/{id}")
	public ResponseEntity<User> getById(@PathVariable Long id) throws RecordNotFoundException {
		Optional<User> optionalUser = userService.findById(id);
		return optionalUser.map(ResponseEntity::ok).orElseThrow(() -> new RecordNotFoundException(USER_NOT_FOUND_FOR_ID, id));
	}

	@PostMapping(value="user/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable User updateUser) {
		return userService.findById(updateUser.getId())
			.map(user -> {
				userService.updateUser(user);
				return ResponseEntity.ok().build();
			})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, USER_NOT_FOUND));
	}
//	get
//	update
//	delete
}

