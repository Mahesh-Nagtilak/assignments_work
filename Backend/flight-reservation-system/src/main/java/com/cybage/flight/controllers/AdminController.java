package com.cybage.flight.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.flight.services.AdminService;


@RestController
@RequestMapping(path = "api/admin")
@CrossOrigin("*")
public class AdminController {
	@Autowired
	private AdminService adminService;
	@GetMapping("/unlockAccount/{email}")
	public ResponseEntity<?> validateOtp(@PathVariable String email)
	{
		adminService.unlockAccount(email);
		return ResponseEntity.ok("User Account is UnLocked");
	}

}
