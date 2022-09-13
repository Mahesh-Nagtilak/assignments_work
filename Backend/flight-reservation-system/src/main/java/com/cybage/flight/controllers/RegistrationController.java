package com.cybage.flight.controllers;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.cybage.flight.dtos.RegistrationRequest;
import com.cybage.flight.exception.AddressNotFound;
import com.cybage.flight.exception.EmailAlreadyExistException;
import com.cybage.flight.exception.EmailNotValidException;
import com.cybage.flight.exception.InvalidTokenException;

@RestController
@RequestMapping(path = "api/registration")
@CrossOrigin("*")
public class RegistrationController {

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping
	public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
		String url = "http://localhost:8079/api/flight/register";
		try
		{
			return	(ResponseEntity<String>) restTemplate.postForEntity(url, request, String.class);
		}
		catch(HttpStatusCodeException e)
		{  
			if(e.getRawStatusCode()==502)
			throw new EmailAlreadyExistException("Email Already Exist");
			else if(e.getRawStatusCode()==400)
			{
				throw new AddressNotFound("Email Address Not Found");
			}
			else if(e.getRawStatusCode()==401)
			{
				throw new EmailNotValidException("Email  Not Valid");
			}
			else
			{
				throw new RuntimeException ("Couldn't process your request..Try Again !!");
			}
		}
		 
	}

	@GetMapping(path = "confirm")
	public ResponseEntity<String> confirm(@RequestParam("token") String token) {

		String url = "http://localhost:8079/api/flight/confirmUser";
		try {
		return (ResponseEntity<String>) restTemplate.postForEntity(url, token, String.class);
		}
		catch(HttpStatusCodeException e)
		{
			throw new InvalidTokenException("<br/><h1  style='text-align: center'>Link is Expired</h1>");
		}
	}

}
