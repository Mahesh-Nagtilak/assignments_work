package com.cybage.flight.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OtpValidationRequest {
   private int otp;
   private String email;
}
