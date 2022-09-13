package com.cybage.flight.exception;

public class AccountBlockedException extends RuntimeException {

	private static final long serialVersionUID = -2176086681488816003L;

	public AccountBlockedException(String message) {
			super(message);
		}

}
