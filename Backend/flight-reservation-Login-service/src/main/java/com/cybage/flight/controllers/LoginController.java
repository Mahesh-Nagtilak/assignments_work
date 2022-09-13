package com.cybage.flight.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.flight.config.JwtUtil;
import com.cybage.flight.dto.LoginDto;
import com.cybage.flight.dto.UserResponse;
import com.cybage.flight.email.EmailService;

import com.cybage.flight.entities.OtpValidationRequest;
import com.cybage.flight.entities.User;
import com.cybage.flight.exception.AccountBlockedException;

import com.cybage.flight.exception.InvalidCredentialsException;
import com.cybage.flight.exception.WrongOtpException;
import com.cybage.flight.services.LoginAttemptService;
import com.cybage.flight.services.LoginService;
import com.cybage.flight.services.UserService;

@RestController
@RequestMapping(path = "api/user")
@CrossOrigin("*")
public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public LoginService otpService;

	@Autowired
	public EmailService myEmailService;

	@Autowired
	private UserService userDetailsService;
	@Autowired
	private LoginAttemptService loginAttemptService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDto loginRequest)   {
		
		try {
			if(loginAttemptService.isBlocked(loginRequest.getEmail()))
			{
				userDetailsService.lockAccount(loginRequest.getEmail());
				throw new AccountBlockedException("Your Account is Locked");
			}
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					loginRequest.getEmail(), loginRequest.getPassword()));
			
		}
		catch (DisabledException e) {
			throw new AccountBlockedException("USER_DISABLED");
		}
		catch (BadCredentialsException e) {
			loginAttemptService.loginFailed(loginRequest.getEmail());
			throw new InvalidCredentialsException("INVALID_CREDENTIALS");
		}
            
		return new ResponseEntity<String>(loginService.login(loginRequest), HttpStatus.OK);
		
	}

	@PostMapping("/validateOtp")
	public ResponseEntity<?> validateOtp(@RequestBody OtpValidationRequest validationRequest) {
		String email = validationRequest.getEmail();
		int otp = validationRequest.getOtp();
		
		Boolean isValidOtp=loginService.validateOtp(email,otp);
		if(isValidOtp)
		{
			UserDetails userdetails = userDetailsService.loadUserByUsername(email);
			String token = jwtUtil.generateToken(userdetails);
		User user=	userDetailsService.findByEmail(userdetails.getUsername());
		UserResponse userResponse=new	UserResponse(token, user.getId(),user.getUserRole().toString(),user.getFirstName() ,user.getLastName(),user.getEmail());
			return ResponseEntity.ok(userResponse);
		}
		else
			throw new WrongOtpException("Wrong Otp Entered...Try Again..!!");
		
	}
	
}
