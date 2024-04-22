package com.duncancodes.librarymanagement.services;

import com.duncancodes.librarymanagement.entities.User;
import com.duncancodes.librarymanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserService {

	@Autowired
	UserRepository userRepo;

	public User saveUser(User user) {
		return userRepo.save(user);
	}

	public Optional<User> findByUser(String username) {
		return userRepo.findByUserName(username);
	}
}
