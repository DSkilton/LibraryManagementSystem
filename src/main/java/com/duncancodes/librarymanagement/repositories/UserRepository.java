package com.duncancodes.librarymanagement.repositories;

import com.duncancodes.librarymanagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByFamilyName(String familyName);

}
