package com.cybage.flight.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.flight.daos.FlightRepository;
import com.cybage.flight.dtos.FlightAvalaibiltyDTO;
import com.cybage.flight.entities.Flight;

@Service
public class FlightService1 {
	@Autowired
	FlightRepository flightRepository;

	// Method to add flight and flight schedule details
	public Flight addFlight(Flight flight) {
		return flightRepository.save(flight);
	}

	// Method to edit flight and flight schedule details
	public Flight editFlight(int flightId, Flight flight) {
		return flightRepository.save(flight);
	}

	// Method to delete flight details
	public void deleteFlight(String flightNumber) {
		flightRepository.deleteById(flightNumber);
	}

	// Method to get list of all flights
	public List<Flight> getAllFlights() {
		return flightRepository.findAll();
	}

	public Flight findByFlightNumber(String flightNumber) {
		return flightRepository.findByFlightNumber(flightNumber);
	}

	// sort by flight fare
	public List<FlightAvalaibiltyDTO> sortFlightsByFare(List<FlightAvalaibiltyDTO> flights) {
		return flights.stream().sorted((f1, f2) -> f1.getFare().compareTo(f2.getFare())).collect(Collectors.toList());
	}

	public List<FlightAvalaibiltyDTO> flightByRefundableFare(List<FlightAvalaibiltyDTO> flights) {

		return flights.stream().filter(flight -> flight.getIsRefundable() == true).collect(Collectors.toList());
	}

	// filter for flight name
	public List<FlightAvalaibiltyDTO> flightByName(List<FlightAvalaibiltyDTO> flights, String flightName) {
		return flights.stream().filter(flight -> flight.getFlightFlightName().equalsIgnoreCase(flightName))
				.collect(Collectors.toList());
	}

	// filter for flight stops
	public List<FlightAvalaibiltyDTO> flightByStops(List<FlightAvalaibiltyDTO> flights, int stops) {

		return flights.stream().filter(flight -> flight.getStops() == stops).collect(Collectors.toList());
	}

	// filter for evening flights
	public List<FlightAvalaibiltyDTO> flightByEvening(List<FlightAvalaibiltyDTO> flights) {
		List<FlightAvalaibiltyDTO> flightsByMorning = new ArrayList<>();
		for (FlightAvalaibiltyDTO flightAvalaibiltyDTO : flights) {
			if (flightAvalaibiltyDTO.getDepartureTime().getHours() >= 18
					&& flightAvalaibiltyDTO.getDepartureTime().getHours() < 24) {
				flightsByMorning.add(flightAvalaibiltyDTO);
			}
		}
		return flightsByMorning;
	}

	// filter for AfterNoon flights
	public List<FlightAvalaibiltyDTO> flightByAfterNoon(List<FlightAvalaibiltyDTO> flights) {
		List<FlightAvalaibiltyDTO> flightsByMorning = new ArrayList<>();
		for (FlightAvalaibiltyDTO flightAvalaibiltyDTO : flights) {
			if (flightAvalaibiltyDTO.getDepartureTime().getHours() >= 12
					&& flightAvalaibiltyDTO.getDepartureTime().getHours() < 18) {
				flightsByMorning.add(flightAvalaibiltyDTO);
			}
		}
		return flightsByMorning;
	}

	// filter for Morning flights
	public List<FlightAvalaibiltyDTO> flightByMorning(List<FlightAvalaibiltyDTO> flights) {
		List<FlightAvalaibiltyDTO> flightsByMorning = new ArrayList<>();
		for (FlightAvalaibiltyDTO flightAvalaibiltyDTO : flights) {
			if (flightAvalaibiltyDTO.getDepartureTime().getHours() >= 6
					&& flightAvalaibiltyDTO.getDepartureTime().getHours() < 12) {
				flightsByMorning.add(flightAvalaibiltyDTO);
			}
		}
		return flightsByMorning;

	}

	// filter for Night flights
	public List<FlightAvalaibiltyDTO> flightByNight(List<FlightAvalaibiltyDTO> flights) {
		List<FlightAvalaibiltyDTO> flightsByMorning = new ArrayList<>();
		for (FlightAvalaibiltyDTO flightAvalaibiltyDTO : flights) {
			if (flightAvalaibiltyDTO.getDepartureTime().getHours() >= 0
					&& flightAvalaibiltyDTO.getDepartureTime().getHours() < 6) {
				flightsByMorning.add(flightAvalaibiltyDTO);
			}
		}
		return flightsByMorning;
	}

}
