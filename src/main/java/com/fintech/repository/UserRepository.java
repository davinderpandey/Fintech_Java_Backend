package com.fintech.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintech.entity.User;


public interface UserRepository extends JpaRepository<User, UUID> {

	public User findByEmail(String email);

}
