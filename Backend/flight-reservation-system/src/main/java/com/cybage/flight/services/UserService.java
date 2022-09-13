package com.cybage.flight.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cybage.flight.EntityDtoConvertor.UserMapper;
import com.cybage.flight.daos.UserRepository;
import com.cybage.flight.dtos.LoginRequest;
import com.cybage.flight.dtos.UserRegistrationDTO;
import com.cybage.flight.entities.User;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserMapper mapper;

	public void deleteUser(int id) {
		userRepository.deleteById(id);

	}

	public void updateUser(UserRegistrationDTO userDto, int id) {
		User user = mapper.toUserEntity(userDto);
		User existingUser = userRepository.findById(id);
		
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		
		userRepository.save(existingUser);
        
	}
	public void updateUserPassword(LoginRequest userDto, int id) {
		User existingUser = userRepository.findById(id);
		
		String encodedPassword = bCryptPasswordEncoder.encode(userDto.getPassword());
    	existingUser.setPassword(encodedPassword);
    	userRepository.save(existingUser);
        
	}

}
