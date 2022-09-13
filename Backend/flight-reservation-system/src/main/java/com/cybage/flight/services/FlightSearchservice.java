package com.cybage.flight.services;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.flight.EntityDtoConvertor.FlightMapper;
import com.cybage.flight.daos.FlightScheduleRepository;
import com.cybage.flight.dtos.FlightAvalaibiltyDTO;
import com.cybage.flight.dtos.FlightSearchDTO;
import com.cybage.flight.entities.Flight;
import com.cybage.flight.entities.FlightSchedule;

@Service
public class FlightSearchservice {

	@Autowired
	private FlightScheduleRepository flightScheduleRepository;
	@Autowired
	private FlightMapper flightMapper;

	public List<FlightAvalaibiltyDTO> searchFlight(FlightSearchDTO flightSearchDto) {
		FlightSchedule flightSchedule = flightMapper.toFlightScheduleEntity(flightSearchDto);

		String source = flightSchedule.getSource();
		String destination = flightSchedule.getDestination();
		Date departureDate = flightSchedule.getDepartureDate();
		String flightType = flightSchedule.getFlight().getFlightType();
		int availableSeats = flightSchedule.getAvailableSeats();

		List<FlightSchedule> flightSchedulesList = flightScheduleRepository
				.findAllBySourceAndDestinationAndDepartureDateAndFlightFlightTypeAndAvailableSeatsGreaterThanEqual(
						source, destination, departureDate,  flightType,availableSeats);

		List<FlightAvalaibiltyDTO> availableFlights = flightMapper.toFlightAvalaibiltyDto(flightSchedulesList);
		return availableFlights;

	}

}
