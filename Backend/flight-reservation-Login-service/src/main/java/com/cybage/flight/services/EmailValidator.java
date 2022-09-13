package com.cybage.flight.services;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String email) {

    	boolean result = true;
    	   try {
    	      InternetAddress emailAddr = new InternetAddress(email);
    	      emailAddr.validate();
    	   } catch (AddressException ex) {
    	      result = false;
    	   }
    	   return result;
    }
}
