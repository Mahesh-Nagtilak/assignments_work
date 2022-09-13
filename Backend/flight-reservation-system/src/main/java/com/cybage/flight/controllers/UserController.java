package com.cybage.flight.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.flight.dtos.LoginRequest;
import com.cybage.flight.dtos.UserRegistrationDTO;
import com.cybage.flight.services.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/userProfile")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@PutMapping("updateUser/{id}")
	public ResponseEntity<?> updateUser(@RequestBody UserRegistrationDTO userDto, @PathVariable int id)  {
		userService.updateUser(userDto, id);
		logger.info("User Details Suceesfully Updated id:"+id);
		return  ResponseEntity.ok("User Details Suceesfully Updated");
		
	}
	@PutMapping("updatePassword/{id}")
	public ResponseEntity<?> updateUserPassword(@RequestBody LoginRequest userDto,@PathVariable int id)  {
		userService.updateUserPassword(userDto,id);
		logger.info("User Password Suceesfully Updated id:"+id);
		return  ResponseEntity.ok("User Password Suceesfully Updated");
		
	}

	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int id) {

		userService.deleteUser(id);
		logger.info("User deleted id:"+id);
		return  ResponseEntity.ok("User  Deleted Successfully");
		
	}
}
