package com.duncancodes.librarymanagement.services;

import com.duncancodes.librarymanagement.entities.User;
import com.duncancodes.librarymanagement.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(User.class);

	@Autowired
	private UserRepository userRepo;

	public User saveUser(User user) {
		return userRepo.save(user);
	}

	public Optional<User> findByFamilyName(String familyName) {
		return userRepo.findByFamilyName(familyName);
	}

	public Optional<User> findById(Long id) {
		return userRepo.findById(id);
	}

	public List<User> findAll() {
		return userRepo.findAll();
	}

	public void createUser(User user, HttpServletRequest request) {
		LOGGER.debug("Creating new user");
		User newUser = new User();
		newUser.setAddress(user.getAddress());
		newUser.setContact(user.getContact());
		newUser.setTitle(user.getTitle());
		newUser.setFamilyName(user.getFamilyName());

		User savedUser = saveUser(user);
		LOGGER.debug("New user created with id: {}", savedUser.getId());
	}

	public void delete(User user) {
		userRepo.delete(user);
	}

	public User updateUser(User user) {
		return userRepo.save(user);
	}
}
