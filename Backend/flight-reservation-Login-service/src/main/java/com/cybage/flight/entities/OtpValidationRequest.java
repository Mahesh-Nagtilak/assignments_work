package com.cybage.flight.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OtpValidationRequest {
   private int otp;
   private String email;
}
