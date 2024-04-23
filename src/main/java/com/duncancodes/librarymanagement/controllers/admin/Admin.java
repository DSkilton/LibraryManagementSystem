package com.duncancodes.librarymanagement.controllers.admin;

import com.duncancodes.librarymanagement.entities.User;
import com.duncancodes.librarymanagement.exceptions.RecordNotFoundException;
import com.duncancodes.librarymanagement.repositories.UserRepository;
import com.duncancodes.librarymanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;

import static com.duncancodes.librarymanagement.utils.Constant.USER_NOT_FOUND_FOR_ID;

@Controller
@RequestMapping(value="/admin")
public class Admin {

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

	@GetMapping(value="/retrieveAllUsers")
	public ResponseEntity<List<User>> retrieve() {
		List<User> users = userService.findAll();
		return ResponseEntity.ok(users);
	}

	@GetMapping(value="user/{id}")
	public ResponseEntity<User> getById(@PathVariable Long id) throws RecordNotFoundException {
		Optional<User> optionalUser = userService.findById(id);
		return optionalUser.map(ResponseEntity::ok).orElseThrow(() -> new RecordNotFoundException(USER_NOT_FOUND_FOR_ID, id));
	}

	@DeleteMapping(value="user/{id}")
	public HttpStatus deleteUser(@PathVariable Long id) throws RecordNotFoundException {
		Optional<User> optionalUser = userService.findById(id);

		optionalUser.ifPresent(optionalUser -> {
			User user = optionalUser;

		} );

		try {
			userRepo.delete(user);
			return HttpStatus.OK;
		} catch (Exception e) {
			throw new RecordNotFoundException(USER_NOT_FOUND_FOR_ID, id);
		}
	}
}
