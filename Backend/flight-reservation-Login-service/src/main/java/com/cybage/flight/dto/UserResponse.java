package com.cybage.flight.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
	private String token;
	private int userId;
	private String userRole;
	private String firstName;
	private String lastName;
	private String email;

}
