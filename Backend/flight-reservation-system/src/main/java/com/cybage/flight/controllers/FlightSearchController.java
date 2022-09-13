package com.cybage.flight.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.flight.dtos.FlightAvalaibiltyDTO;
import com.cybage.flight.dtos.FlightSearchDTO;
import com.cybage.flight.exception.RecordNotFoundException;
import com.cybage.flight.services.FlightSearchservice;
import com.cybage.flight.services.FlightService1;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "api/search/")
public class FlightSearchController {

	@Autowired
	private FlightSearchservice flightSearchService;
	Logger logger = LogManager.getLogger(FlightController.class);
	@Autowired
	FlightService1 flightService;
   

	
	@PostMapping(path = "/searchFlight")
	public ResponseEntity<?> searchFlight(@RequestBody FlightSearchDTO flightSearchDto) {
		List<FlightAvalaibiltyDTO> flights = flightSearchService.searchFlight(flightSearchDto);
		if (flights == null) {
			throw new RecordNotFoundException("Fligths are Not Available for given Schedule!!");
		}
		return new ResponseEntity<List<FlightAvalaibiltyDTO>>(flights, HttpStatus.OK);
	}

	@PostMapping("/flightSortByFare")
	public ResponseEntity<?> sortFlightsByFare(@RequestBody FlightSearchDTO flightSearchDto) {
		logger.info("Flight sort by fare api  called");

		List<FlightAvalaibiltyDTO> flights = flightSearchService.searchFlight(flightSearchDto);
		flights = flightService.sortFlightsByFare(flights);

		return ResponseEntity.ok(flights);

	}

	@PostMapping("/flightByName")
	public ResponseEntity<?> flightByName(@RequestBody FlightSearchDTO flightSearchDto) {
		logger.info("Flight by name  api  called");
		String flightName = flightSearchDto.getFlightFlightName();
		List<FlightAvalaibiltyDTO> flights = flightSearchService.searchFlight(flightSearchDto);
		flights = flightService.flightByName(flights, flightName);

		if (flights == null)
			throw new RecordNotFoundException("Not Found");

		return ResponseEntity.ok(flights);

	}

	@PostMapping("/flightByRefundableFare")
	public ResponseEntity<?> flightByRefundableFare(@RequestBody FlightSearchDTO flightSearchDto) {
		logger.info("Flight by refundable fare  api  called");

		List<FlightAvalaibiltyDTO> flights = flightSearchService.searchFlight(flightSearchDto);
		flights = flightService.flightByRefundableFare(flights);
		if (flights == null)
			throw new RecordNotFoundException("Not Found");

		return ResponseEntity.ok(flights);

	}

	@PostMapping("/flightByStops")
	public ResponseEntity<?> flightByStops(@RequestBody FlightSearchDTO flightSearchDto) {
		logger.info("Filter Flight ByMorning  api  called");
		int stops = flightSearchDto.getStops();
		// searched flights on which filter will apply
		List<FlightAvalaibiltyDTO> flights = flightSearchService.searchFlight(flightSearchDto);
		flights = flightService.flightByStops(flights, stops);

		if (flights == null)
			throw new RecordNotFoundException("Not Found");

		return new ResponseEntity<>(flights, HttpStatus.OK);
	}

	@PostMapping("/flightByMorning")
	public ResponseEntity<?> flightByMorning(@RequestBody FlightSearchDTO flightSearchDto) {
		logger.info("Filter Flight ByMorning  api  called");

		// searched flights on which filter will apply
		List<FlightAvalaibiltyDTO> flights = flightSearchService.searchFlight(flightSearchDto);
		flights = flightService.flightByMorning(flights);

		if (flights == null)
			throw new RecordNotFoundException("Not Found");

		return new ResponseEntity<>(flights, HttpStatus.OK);
	}

	@PostMapping("/flightByAfterNoon")
	public ResponseEntity<?> flightByNoon(@RequestBody FlightSearchDTO flightSearchDto) {
		logger.info("Filter Flight ByNoon  api  called");

		// searched flights on which filter will apply
		List<FlightAvalaibiltyDTO> flights = flightSearchService.searchFlight(flightSearchDto);

		flights = flightService.flightByAfterNoon(flights);
		if (flights == null) {
			throw new RecordNotFoundException("Not Found");
		}
		return new ResponseEntity<>(flights, HttpStatus.OK);
	}

	@PostMapping("/flightByEvening")
	public ResponseEntity<?> flightByEvening(@RequestBody FlightSearchDTO flightSearchDto) {

		// searched flights on which filter will apply
		List<FlightAvalaibiltyDTO> flights = flightSearchService.searchFlight(flightSearchDto);

		logger.info(" Filter Flight ByEvening api  called");
		flights = flightService.flightByEvening(flights);
		if (flights == null) {
			throw new RecordNotFoundException("Not Found");
		}
		return new ResponseEntity<>(flights, HttpStatus.OK);
	}

	@PostMapping("/flightByNight")
	public ResponseEntity<?> flightByNight(@RequestBody FlightSearchDTO flightSearchDto) {
		logger.info(" FIlter Flight ByNight api  called");

		// searched flights on which filter will apply
		List<FlightAvalaibiltyDTO> flights = flightSearchService.searchFlight(flightSearchDto);

		flights = flightService.flightByNight(flights);
		if (flights == null) {
			throw new RecordNotFoundException("Not Found");
		}
		return new ResponseEntity<>(flights, HttpStatus.OK);
	}

}
