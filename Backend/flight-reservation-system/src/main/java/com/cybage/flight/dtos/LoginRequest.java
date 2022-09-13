package com.cybage.flight.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginRequest {
	@NotBlank(message = "Email Cannot be blank")
    private  String email;
	@NotBlank(message = "Password cannot be blank")
	@Size(min = 7,max = 14)
    private  String password;
}
