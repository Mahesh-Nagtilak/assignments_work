package com.cybage.flight.email;

public interface EmailSender {
    void send(String subject,String to, String email);
}
