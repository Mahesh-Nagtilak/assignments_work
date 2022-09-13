package com.cybage.flight.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<String> handleNoSuchElemntException(RecordNotFoundException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmailAlreadyExistException.class)
	public ResponseEntity<String> emailAlreadyExistException(EmailAlreadyExistException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(EmailNotValidException.class)
	public ResponseEntity<String> emailNotValidException(EmailNotValidException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> checkedException(Exception exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> uncheckedException(RuntimeException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<String> invalidCredentialsException(InvalidCredentialsException exception){
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.UNAUTHORIZED);  
	}
	@ExceptionHandler(AddressNotFound.class)
	public ResponseEntity<String> addressNotFound(AddressNotFound exception){
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);  
	}
	@ExceptionHandler(WrongOtpException.class)
	public ResponseEntity<String> wrongOtpException(WrongOtpException exception){
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.UNAUTHORIZED);  
	}
}
