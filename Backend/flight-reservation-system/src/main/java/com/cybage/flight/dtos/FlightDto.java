package com.cybage.flight.dtos;

import java.sql.Date;
import java.sql.Time;

import org.springframework.stereotype.Component;

import com.cybage.flight.entities.Flight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto 
{
	private String flightNumber;
	private String flightName;
	private String flightType;
	private int totalSeats;
	private int stops;
	private String source;
	private String destination;
	private Date departureDate;
	private Date arrivalDate;
	private Time departureTime;
	private Time arrivalTime;
	private int availableSeats;
	private double fare;
	private Boolean isRefundable; 
	
	public FlightDto toFlightDto(Flight flight) {
		FlightDto flightDto = new FlightDto();
		flightDto.setFlightNumber(flight.getFlightNumber());
		flightDto.setFlightName(flight.getFlightName());
		flightDto.setFlightType(flight.getFlightType());
		flightDto.setTotalSeats(flight.getTotalSeats());
		flightDto.setSource(flight.getFlightSchedules().get(0).getSource());
		flightDto.setDestination(flight.getFlightSchedules().get(0).getDestination());
		flightDto.setDepartureDate(flight.getFlightSchedules().get(0).getDepartureDate());
		flightDto.setArrivalDate(flight.getFlightSchedules().get(0).getArrivalDate());
		flightDto.setDepartureTime(flight.getFlightSchedules().get(0).getDepartureTime());
		flightDto.setArrivalTime(flight.getFlightSchedules().get(0).getArrivalTime());
		flightDto.setAvailableSeats(flight.getFlightSchedules().get(0).getAvailableSeats());
		flightDto.setStops(flight.getFlightSchedules().get(0).getStops());
		flightDto.setFare(flight.getFlightSchedules().get(0).getFare());
		flightDto.setIsRefundable(flight.getFlightSchedules().get(0).getIsRefundable());
		return flightDto;
	}
}
