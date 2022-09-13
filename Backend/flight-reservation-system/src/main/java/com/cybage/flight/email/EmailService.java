package com.cybage.flight.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.cybage.flight.exception.EmailNotValidException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

	private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

	private final JavaMailSender mailSender;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void send(String subject, String to, String emailMessage) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
			helper.setText(emailMessage, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setFrom("Trng2@evolvingsols.com");
			logger.info(subject);
			logger.info(to);
			logger.info(emailMessage);

			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			LOGGER.error("Failed to send email", e);
			throw new IllegalStateException("Failed to send email");
			//throw new EmailNotValidException("Failed to send email");
		}
	}
}
