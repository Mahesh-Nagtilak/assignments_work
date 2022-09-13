package com.cybage.flight.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

	private int userId; // userid;
	private int flightId; // flight Schedule id
	private double amountPaid;
	private String email;
	
}
