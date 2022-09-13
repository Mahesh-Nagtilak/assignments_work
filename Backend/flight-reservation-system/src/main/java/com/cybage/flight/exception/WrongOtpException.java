package com.cybage.flight.exception;

public class WrongOtpException extends RuntimeException {
 
	private static final long serialVersionUID = -5641819372837734628L;

public WrongOtpException(String message)
  {
	  super(message);
  }
}
