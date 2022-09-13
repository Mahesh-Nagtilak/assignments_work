package com.cybage.flight.services;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.cybage.flight.dto.LoginDto;
import com.cybage.flight.email.EmailSender;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class LoginService {

	private static final Integer EXPIRE_MINS = 5;
	// cache based on username
	private LoadingCache<String, Integer> otpCache;
	@Autowired
	private LoginAttemptService loginAttemptService;
	@Autowired
	private EmailValidator emailValidator;
	@Autowired
	private EmailSender emailSender;

	public LoginService() {
		super();
		otpCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
				.build(new CacheLoader<String, Integer>() {
					public Integer load(String key) {
						return 0;
					}
				});
	}

	public String login(LoginDto request) {
		
		boolean isValidEmail = emailValidator.test(request.getEmail());

		if (!isValidEmail) {
			throw new IllegalStateException("Email not valid");
		}
		loginAttemptService.loginSucceeded(request.getEmail());
		String otpMessage = loginUser(request.getEmail(), request.getPassword());

		return otpMessage;
	}

	public String loginUser(String email, String password) {

		int otp = generateOTP(email);
         
		// SEND EMAIL
		String subject = "Verify OTP for Login";
		emailSender.send(subject, email, buildEmail(otp));
		return "OTP is :" + otp;

	}

	private String buildEmail(int otp) {

		return "<div style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"><p>Hello,</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> . Your One Time Password is :<blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">"
				+ otp + "</p></blockquote></p>\n OTP will expire in 5 minutes. <p>See you there</p></div>";

	}

	// This method is used to push the opt number against Key. Rewrite the OTP if it
	// exists
	// Using user email as key
	public int generateOTP(String key) {

		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		otpCache.put(key, otp);
		return otp;
	}

	// This method is used to return the OPT number against Key->Key values is
	// username
	public int getOtp(String key) {
		try {
			return otpCache.get(key);
		} catch (Exception e) {
			return 0;
		}
	}

	// This method is used to clear the OTP catched already
	public void clearOTP(String key) {
		otpCache.invalidate(key);
	}
  //later thrown excetion instead of boolean 
	public Boolean validateOtp(String email,int otp) {

		final String SUCCESS = "Entered Otp is valid";
		final String FAIL = "Entered Otp is NOT valid. Please Retry!";

		
		if (otp >= 0) {
			int serverOtp = getOtp(email);

			if (serverOtp > 0) {
				if (otp == serverOtp) {
					clearOTP(email);
					return true;  //SUCCESS;
				} else {
					return false; //FAIL;
				}
			} else {
				return false; //FAIL;
			}
		} else {
			return false; //FAIL;
		}
	}

}
