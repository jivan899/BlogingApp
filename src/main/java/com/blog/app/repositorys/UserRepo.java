package com.blog.app.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.entities.User;

public interface UserRepo extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);

}
