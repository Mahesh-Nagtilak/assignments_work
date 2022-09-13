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
	public ResponseEntity<String> emailAlreadyExist(EmailAlreadyExistException exception){
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_GATEWAY);  
	}
	@ExceptionHandler(WrongOtpException.class)
	public ResponseEntity<String> wrongOtpException(WrongOtpException exception){
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.UNAUTHORIZED);  
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> checkedException(Exception exception){
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);  
	}
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> uncheckedException(RuntimeException exception){
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);  
	}
	@ExceptionHandler(AccountBlockedException.class)
	public ResponseEntity<String> accountLockedException(AccountBlockedException exception){
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.LOCKED);  
	}
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<String> invalidCredentialsException(InvalidCredentialsException exception){
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.UNAUTHORIZED);  
	}
	@ExceptionHandler(AddressNotFound.class)
	public ResponseEntity<String> addressNotFound(AddressNotFound exception){
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);  
	}
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<String> illegalStateException(IllegalStateException exception){
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);  
	}
	

}
