package com.cybage.flight.exception;

public class RecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8518981097107648062L;

	public RecordNotFoundException(String message) {
		super(message);
	}
}
