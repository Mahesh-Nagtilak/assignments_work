package com.cybage.feedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cybage.feedback.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	public User findById(int id);
}
