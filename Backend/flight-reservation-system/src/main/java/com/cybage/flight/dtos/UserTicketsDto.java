package com.cybage.flight.dtos;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTicketsDto {
	private int flightScheduleId;     //flight schedule id
	private  Long bookingId;
	private String flightName;
	private String flightNumber;
	private double bookingAmount;
	private int passengerId;
	private Long prnNumber;
	private String firstName;
	private String lastName;
	private String gender;
	private byte age;
	private String seatNumber;
	private String source;
	private String destination;
	private Date departureDate;
	private Date arrivalDate;
	private Time departureTime;
	private Time arrivalTime;
	private String flightClass;

}
