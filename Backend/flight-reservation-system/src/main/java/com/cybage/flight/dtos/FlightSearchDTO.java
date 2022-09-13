package com.cybage.flight.dtos;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightSearchDTO {
	
	private String source;
	private String destination;
	private Date departureDate;
	private String flightFlightType;
	private int availableSeats;
	//to apply filter on search page
	private String flightFlightName;
	private int stops;

}
