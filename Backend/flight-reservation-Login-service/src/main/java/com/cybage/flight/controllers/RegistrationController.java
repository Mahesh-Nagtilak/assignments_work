package com.cybage.flight.controllers;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cybage.flight.entities.RegistrationRequest;
import com.cybage.flight.services.RegistrationService;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/flight/")
public class RegistrationController {
  
	@Autowired
	private  RegistrationService registrationService;

	@PostMapping(path ="register")
	public String register(@RequestBody RegistrationRequest request) {
		
		return registrationService.register(request);
	}

	@PostMapping(path = "confirmUser")
	public String confirm(@RequestBody String   registerToken) {
		   
		return registrationService.confirmToken( registerToken);
	}

}
