package com.cybage.flight.services;

import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.flight.exception.*;
import com.cybage.flight.daos.FlightRepository;
import com.cybage.flight.dtos.FlightDto;
import com.cybage.flight.entities.Flight;
import com.cybage.flight.entities.FlightSchedule;

@Service
public class FlightService 
{
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	FlightDto flightDto;
	
	public FlightDto addFlight(FlightDto flightDto)
	{
		List<FlightSchedule> flightScheduleList = new ArrayList<>();
		FlightSchedule flightSchedule = new FlightSchedule();
		flightSchedule.setSource(flightDto.getSource());
		flightSchedule.setDestination(flightDto.getDestination());
		flightSchedule.setArrivalDate(flightDto.getArrivalDate());
		flightSchedule.setDepartureDate(flightDto.getDepartureDate());
		flightSchedule.setArrivalTime(flightDto.getArrivalTime());
		flightSchedule.setDepartureTime(flightDto.getDepartureTime());
		flightSchedule.setAvailableSeats(flightDto.getAvailableSeats());
		flightSchedule.setStops(flightDto.getStops());
		flightSchedule.setFare(flightDto.getFare());
		flightSchedule.setIsRefundable(flightDto.getIsRefundable());
		flightScheduleList.add(flightSchedule);
		
		Flight flight = new Flight();
		flight.setFlightNumber(flightDto.getFlightNumber());
		flight.setFlightName(flightDto.getFlightName());
		flight.setFlightType(flightDto.getFlightType());
		flight.setTotalSeats(flightDto.getTotalSeats());
		flightSchedule.setFlight(flight);
		flight.setFlightSchedules(flightScheduleList);
		
		
		
		return flightDto.toFlightDto(flightRepository.save(flight));
	}
	
	//Method to edit flight details
	public Flight editFlight(String flightNumber,Flight flight)
	{
		return flightRepository.save(flight);
	}
	
	//Method to delete flight details
	public void deleteFlight(String flightNumber)
	{
		Flight flightTobeDeleted = flightRepository.findById(flightNumber).orElseThrow(()->new FlightNotFoundException("Flight does not exist for flight number "+flightNumber));
		flightRepository.delete(flightTobeDeleted);
	}
	
	//Method to get list of all flights
	public List<Flight> getAllFlights()
	{
		return flightRepository.findAll();
	}
	
	public Flight findByFlightNumber(String flightNumber)
	{
		Flight flightTobeSearchedByFlightByFlightNumber = flightRepository.findById(flightNumber).orElseThrow(()->new FlightNotFoundException("Flight does not exist for flight number "+flightNumber));
		return flightRepository.findByFlightNumber(flightTobeSearchedByFlightByFlightNumber.getFlightNumber());
	}
	
	public List<FlightSchedule> findByDayTiming(Time startTime, Time endTime) throws ParseException
	{		
		return flightRepository.findByDayTiming(startTime, endTime);
	}
	
	public List<Flight> findByFlightName(String flightName)
	{
		return flightRepository.findByFlightName(flightName);
	}

}

