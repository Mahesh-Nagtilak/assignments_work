package com.cybage.flight.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.flight.EntityDtoConvertor.OfferMapper;
import com.cybage.flight.dtos.FlightOfferDTO;
import com.cybage.flight.entities.FlightOffer;
import com.cybage.flight.exception.RecordNotFoundException;
import com.cybage.flight.services.FlightOfferService;

@RestController
@RequestMapping("api/flightOffer")
@CrossOrigin("*")
public class FlightOfferController {

	@Autowired
	private FlightOfferService flightOfferService;
	@Autowired
	private OfferMapper offerMapper;

	@PostMapping("/addOffer")
	public ResponseEntity<String> addFlightOffer(@RequestBody @Valid FlightOfferDTO flightOfferDto) {
		FlightOffer flightOffer = new FlightOffer();
		flightOffer = offerMapper.toOfferEntity(flightOfferDto);

		flightOfferService.addFlightOffer(flightOffer);

		return new ResponseEntity<String>("flightOffer is added", HttpStatus.OK);
	}

	@GetMapping("/getAlloffers")
	public ResponseEntity<List<FlightOfferDTO>> getAllFlightOffers() {

		List<FlightOffer> flightOfferList = new ArrayList<>();

		flightOfferList = flightOfferService.getAllFlightOffers();

		if (flightOfferList.isEmpty()) {
			throw new RecordNotFoundException("flightOffer not found");
		}

		List<FlightOfferDTO> flightOfferDTOList = offerMapper.toOfferDTO(flightOfferList);

		return new ResponseEntity<List<FlightOfferDTO>>(flightOfferDTOList, HttpStatus.OK);
	}

	@PutMapping("/updateOffer/{id}")
	public ResponseEntity<String> updateFlightOffer(@RequestBody FlightOfferDTO flightOfferDto, @PathVariable int id)
			throws RecordNotFoundException {

		FlightOffer flightOffer = new FlightOffer();

		flightOffer = offerMapper.toOfferEntity(flightOfferDto);

		if (flightOffer == null) {
			throw new RecordNotFoundException("flightOffer not found");
		}

		flightOfferService.updateFlightOffer(flightOffer, id);

		return new ResponseEntity<String>("flightOffer is updated", HttpStatus.OK);
	}

	@DeleteMapping("/deleteOffer/{id}")
	public ResponseEntity<String> deleteFlightOffer(@PathVariable int id) {
		flightOfferService.deleteFlightOffer(id);

		return new ResponseEntity<String>("flightOffer deleted", HttpStatus.OK);
	}

	@GetMapping("/getByOfferId/{id}")
	public ResponseEntity<FlightOffer> getOfferById(@PathVariable int id) {
		FlightOffer flightOffer = flightOfferService.getOfferById(id);

		return new ResponseEntity<FlightOffer>(flightOffer, HttpStatus.OK);
	}

}
