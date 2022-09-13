package com.cybage.flight.services;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.security.auth.login.AccountLockedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cybage.flight.daos.UserRepository;
import com.cybage.flight.entities.ConfirmationToken;
import com.cybage.flight.entities.User;
import com.cybage.flight.exception.AccountBlockedException;

import com.cybage.flight.exception.EmailAlreadyExistException;
import com.cybage.flight.exception.RecordNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

	private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private ConfirmationTokenService confirmationTokenService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException  {
       
    	User user = userRepository.findByEmail(email);
    	if(user==null)//new line
    		throw new RecordNotFoundException(String.format(USER_NOT_FOUND_MSG, email));//new line added
		
		if (user != null && user.isEnabled()) {
			if(user.isLocked())  //new line added 
				throw new AccountBlockedException("Account is Locked!!");  //new line added
			GrantedAuthority authority = new SimpleGrantedAuthority(user.getUserRole().toString());
			System.out.println(user.getUserRole().toString());
			UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(
					user.getEmail(), user.getPassword(), Arrays.asList(authority));
			return userDetails;
		} else {
			 new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email));
		}
		return user;

	}


//for register
	public String signUpUser(User user) {
		userRepository.deleteUnconfimedUsers();
		userRepository.deleteUnconfimedUsersfromAppUsers();

//Boolean isuserExist = userRepository.findByEmail(user.getEmail()).isPresent();
		User user1 = userRepository.findByEmail(user.getEmail());

		if (user1 != null) {
			// check of attributes are the same and
			// if email not confirmed send confirmation email.
			//return "email already taken";
			throw new EmailAlreadyExistException("Email Already Registred");
			// throw new IllegalStateException("email already taken");
		}

		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

		user.setPassword(encodedPassword);

		userRepository.save(user);

		String token = UUID.randomUUID().toString();

		ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15), user);

		confirmationTokenService.saveConfirmationToken(confirmationToken);

//        TODO: SEND EMAIL

		return token;
	}

	public int enableAppUser(String email) {
		return userRepository.enableUser(email);
	}


	public void lockAccount(String email) {
		
		userRepository.lockUser(email);
	}


	public User findByEmail(String username) {
	User user=	userRepository.findByEmail(username);
		return user;
	}

}
