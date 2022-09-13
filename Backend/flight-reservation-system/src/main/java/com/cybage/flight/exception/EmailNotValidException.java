package com.cybage.flight.exception;

public class EmailNotValidException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EmailNotValidException(String message) {
		super(message);
	}
}
