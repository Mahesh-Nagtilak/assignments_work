package com.cybage.flight.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cybage.flight.email.EmailSender;
import com.cybage.flight.entities.ConfirmationToken;
import com.cybage.flight.entities.RegistrationRequest;
import com.cybage.flight.entities.User;
import com.cybage.flight.entities.UserRole;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class RegistrationService {
	private static final Integer EXPIRE_MINS = 15;

	@Autowired
	private UserService userService;
	@Autowired
	private EmailValidator emailValidator;
	@Autowired
	private ConfirmationTokenService confirmationTokenService;
	@Autowired
	private EmailSender emailSender;

	public String register(RegistrationRequest request) {

		boolean isValidEmail = emailValidator.test(request.getEmail());

		if (!isValidEmail) {
			throw new IllegalStateException("email not valid");
		}

		String token = userService.signUpUser(new User(request.getFirstName(), request.getLastName(),
				request.getEmail(), request.getPassword(), UserRole.ROLE_USER

		));

		String link = "172.27.95.71:8091/api/registration/confirm?token=" + token;

		// Below code send the mail
		emailSender.send("Confirm your email for registration", request.getEmail(),
				buildEmail(request.getFirstName(), link));

		return token;
	}

	@Transactional
	public String confirmToken(String token) {
		ConfirmationToken confirmationToken = confirmationTokenService.getToken(token)
				.orElseThrow(() -> new IllegalStateException("token not found"));

		if (confirmationToken.getConfirmedAt() != null) {
			throw new IllegalStateException("email already confirmed");
		}

		LocalDateTime expiredAt = confirmationToken.getExpiresAt();

		if (expiredAt.isBefore(LocalDateTime.now())) {
			throw new IllegalStateException("token expired");
		}

		confirmationTokenService.setConfirmedAt(token);
		userService.enableAppUser(confirmationToken.getAppUser().getEmail());
		return "Your Registration has Been Suceesfully Confirmed";
		}
	private String buildEmail(String name, String link) {

		return "<div style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"><p>Hello " + name
				+ ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\""
				+ link
				+ "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p></div>";

	}
}
