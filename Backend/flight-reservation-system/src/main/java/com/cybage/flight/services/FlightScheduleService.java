package com.cybage.flight.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.flight.daos.FlightScheduleRepository;
import com.cybage.flight.dtos.FlightScheduleDto;
import com.cybage.flight.entities.FlightSchedule;

@Service
public class FlightScheduleService 
{
	@Autowired
	FlightScheduleRepository flightScheduleRepository;
	
	@Autowired
	FlightScheduleDto flightScheduleDto;
	
	@Autowired
	FlightService flightService;
	
	public void deleteFlightSchedule(int id)
	{
		System.out.println("id from service "+id);
		flightScheduleRepository.deleteById(id);
	}
	
	public void editFlightSchedule(int id,FlightScheduleDto flightScheduleDto)
	{
		FlightSchedule flightSchedule =  flightScheduleRepository.findById(id);
		flightSchedule.setId(flightScheduleDto.getId());
		flightSchedule.setSource(flightScheduleDto.getSource());
		flightSchedule.setDestination(flightScheduleDto.getDestination());
		flightSchedule.setDepartureDate(flightScheduleDto.getDepartureDate());
		flightSchedule.setArrivalDate(flightScheduleDto.getArrivalDate());
		flightSchedule.setDepartureTime(flightScheduleDto.getDepartureTime());
		flightSchedule.setArrivalTime(flightScheduleDto.getArrivalTime());
		flightSchedule.setAvailableSeats(flightScheduleDto.getAvailableSeats());
		flightSchedule.setFare(flightScheduleDto.getFare());
		flightSchedule.setIsRefundable(flightScheduleDto.getIsRefundable());
		flightScheduleRepository.save(flightSchedule);
	}
	
	//Method to get list of all flights schedule
	public List<FlightSchedule> getAllFlightsSchedule()
	{
		return flightScheduleRepository.findAll();
	}
	
	public boolean existsById(int id)
	{
		return flightScheduleRepository.existsById(id);
	}
}