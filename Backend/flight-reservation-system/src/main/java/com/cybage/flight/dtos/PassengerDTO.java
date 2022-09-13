package com.cybage.flight.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDTO {
	    private int passengerId;
		private String firstName;
		private String lastName;
		private String gender;
		private byte age;
		private String passportNumber;
	    private int userId;
}
