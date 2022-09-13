package com.cybage.flight.dtos;

import java.sql.Date;
import java.sql.Time;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightScheduleDto 
{
	private String flightNumber;
	private int id;
	private String source;
	private String destination;
	private Date departureDate;
	private Date arrivalDate;
	private Time departureTime;
	private Time arrivalTime;
	private int availableSeats;
	private int stops;
	private double fare;
	private Boolean isRefundable; 

}
