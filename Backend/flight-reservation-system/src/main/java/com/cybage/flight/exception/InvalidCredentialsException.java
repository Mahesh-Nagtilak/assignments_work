package com.cybage.flight.exception;

public class InvalidCredentialsException extends RuntimeException {

	
	private static final long serialVersionUID = -8087578588806828042L;

	public InvalidCredentialsException(String message) {
		super(message);
	}
}
