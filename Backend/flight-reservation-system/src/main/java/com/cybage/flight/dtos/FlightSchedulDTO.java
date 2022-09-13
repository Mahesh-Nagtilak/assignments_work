package com.cybage.flight.dtos;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightSchedulDTO {

	private int id;    //flight schedule id
	private String source;
	private String destination;
	private Date departureDate;
	private Date arrivalDate;
	private Time departureTime;
	private Time arrivalTime;
	private int availableSeats;
	private int stops;
	private double fare;
 


}
