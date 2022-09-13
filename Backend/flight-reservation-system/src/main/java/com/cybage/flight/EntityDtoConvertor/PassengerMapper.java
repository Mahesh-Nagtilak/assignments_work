package com.cybage.flight.EntityDtoConvertor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cybage.flight.dtos.PassengerDTO;
import com.cybage.flight.entities.Passenger;

@Component
public class PassengerMapper {
	@Autowired
	ModelMapper mapper;

	public PassengerDTO toPassengerDTO(Passenger passenger) {
		PassengerDTO passengerDTO = mapper.map(passenger, PassengerDTO.class);
		return passengerDTO;
	}

	public Passenger toPassengerEntity(PassengerDTO passengerDto) {
		Passenger passenger = mapper.map(passengerDto, Passenger.class);
		return passenger;
	}

	public List<PassengerDTO> toPassengerDTO(List<Passenger> passengers) {
		return passengers.stream().map(passenger -> toPassengerDTO(passenger)).collect(Collectors.toList());

	}
}
