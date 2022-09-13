package com.cybage.flight.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.cybage.flight.dtos.LoginRequest;
import com.cybage.flight.dtos.OtpValidationRequest;
import com.cybage.flight.email.EmailService;
import com.cybage.flight.exception.EmailAlreadyExistException;
import com.cybage.flight.exception.InvalidCredentialsException;
import com.cybage.flight.exception.WrongOtpException;

@RestController
@RequestMapping(path = "api/login")
@CrossOrigin("*")
public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
   
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	public EmailService myEmailService;

	@PostMapping("/authentication")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) throws Exception {
		String url = "http://localhost:8079/api/user/login";
		
		try
		{
			return (ResponseEntity<String>) restTemplate.postForEntity(url, loginRequest, String.class);
		}
		catch(HttpStatusCodeException e)
		{  if(e.getRawStatusCode()==423)
			 throw new InvalidCredentialsException("Account is Locked ");
			
		
		else
			
			throw new InvalidCredentialsException("Invalid Credentials");
		
		}
	}

	@PostMapping("/validate")
	public ResponseEntity<?> validateOtp(@RequestBody OtpValidationRequest validationRequest) {
		String url = "http://localhost:8079/api/user/validateOtp";
		try
		{
			return (ResponseEntity<String>) restTemplate.postForEntity(url, validationRequest, String.class);

		}
		catch(HttpStatusCodeException e)
		{  if(e.getRawStatusCode()==401)
			 throw new WrongOtpException("Enter valid OTP ");
		
		  else
			throw new RuntimeException ("Couldn't process your request..Try Again !!");
		}
	}
	
}
