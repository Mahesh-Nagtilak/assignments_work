package com.cybage.flight.dtos;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightAvalaibiltyDTO {
	private int id;
	private String source;
	private String destination;
	private Date departureDate;
	private Date arrivalDate;
	
	private Time departureTime;
	private Time arrivalTime;
	private Double fare;
	private int stops;
	private Boolean isRefundable;
	private String flightFlightNumber;
	private String flightFlightName;
	private String flightFlightType;

}
