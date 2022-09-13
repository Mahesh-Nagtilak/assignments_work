package com.cybage.flight.EntityDtoConvertor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cybage.flight.dtos.FlightAvalaibiltyDTO;
import com.cybage.flight.dtos.FlightOfferDTO;
import com.cybage.flight.entities.FlightOffer;
import com.cybage.flight.entities.FlightSchedule;

@Component
public class OfferMapper {

	@Autowired
	private ModelMapper mapper;

	public FlightOffer toOfferEntity(FlightOfferDTO flightOfferDTO) {
		FlightOffer fligthOffer = mapper.map(flightOfferDTO, FlightOffer.class);
		return fligthOffer;

	}

	public FlightOfferDTO toOfferDTO(FlightOffer flightOffer) {
		FlightOfferDTO flightOfferDTO = mapper.map(flightOffer, FlightOfferDTO.class);
		return flightOfferDTO;

	}
	

	public List<FlightOfferDTO> toOfferDTO(List<FlightOffer> flightOffers) {

		return flightOffers.stream().map(x -> toOfferDTO(x)).collect(Collectors.toList());
	}
}
