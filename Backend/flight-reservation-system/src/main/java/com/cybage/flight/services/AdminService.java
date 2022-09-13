package com.cybage.flight.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.flight.daos.UserRepository;

@Service
public class AdminService {

	@Autowired
	private UserRepository userRepository;
public void unlockAccount(String email) {
		
		userRepository.unlockUser(email);
	}
}
