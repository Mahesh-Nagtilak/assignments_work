package com.cybage.flight.controllers;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.cybage.flight.dtos.FlightDto;
import com.cybage.flight.dtos.FlightScheduleDto;
import com.cybage.flight.entities.Flight;
import com.cybage.flight.entities.FlightSchedule;
import com.cybage.flight.excel.FlightIO;
import com.cybage.flight.excel.FlightScheduleIO;
import com.cybage.flight.services.FlightScheduleService;
import com.cybage.flight.services.FlightService;

@RestController
@RequestMapping("/api/flight")
@CrossOrigin("*")
public class FlightController {
	@Autowired
	FlightService flightService;

	@Autowired
	FlightScheduleService flightScheduleService;
	
	@PostMapping("/addFlight")
	public ResponseEntity<FlightDto> addFlight(@RequestBody FlightDto flightDto) {
		System.out.println(flightDto);
		return new ResponseEntity<FlightDto>(flightService.addFlight(flightDto), HttpStatus.CREATED);
	}

	@PutMapping("/editFlight/{flightNumber}")
	public ResponseEntity<Flight> editFlight(@PathVariable String flightNumber, @RequestBody Flight flight) {
		return new ResponseEntity<Flight>(flightService.editFlight(flightNumber, flight), HttpStatus.OK);
	}

	@DeleteMapping("/deleteFlight/{flightNumber}")
	public ResponseEntity<String> deleteFlight(@PathVariable String flightNumber) {
		flightService.deleteFlight(flightNumber);
		return new ResponseEntity<String>("Flight details deleted successfully!", HttpStatus.OK);

	}

	@GetMapping("/getAllFlights")
	public ResponseEntity<List<Flight>> getAllFlights() {
		return new ResponseEntity<List<Flight>>(flightService.getAllFlights(), HttpStatus.OK);
	}

	@GetMapping("/findByFlightNumber/{flightNumber}")
	public ResponseEntity<Flight> findByFlightNumber(@PathVariable String flightNumber) {
		return new ResponseEntity<Flight>(flightService.findByFlightNumber(flightNumber), HttpStatus.OK);
	}

	@GetMapping("/findByDayTiming/{startTime}/{endTime}")
	public ResponseEntity<List<FlightSchedule>> findByDayTiming(@PathVariable Time startTime,
			@PathVariable Time endTime) {
		try {
			return new ResponseEntity<List<FlightSchedule>>(flightService.findByDayTiming(startTime, endTime),
					HttpStatus.OK);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@DeleteMapping("/deleteFlightSchedule/{id}")
	public ResponseEntity<String> deleteFlightSchedule(@PathVariable int id) {
		System.out.println("id from controller " + id);
		flightScheduleService.deleteFlightSchedule(id);
		return new ResponseEntity<String>("Flight schedule details deleted successfully!", HttpStatus.OK);
	}
	
	@PutMapping("/editFlightSchedule/{id}")
	public ResponseEntity<String> editFlightSchedule(@PathVariable int id,
			@RequestBody FlightScheduleDto flightScheduleDto) {
		flightScheduleService.editFlightSchedule(id, flightScheduleDto);
		return new ResponseEntity<String>("Flight schedule details edited successfully!", HttpStatus.OK);
	}

	@GetMapping("/findByFlightName/{flightName}")
	public ResponseEntity<List<Flight>> findByFlightName(@PathVariable String flightName) {
		return new ResponseEntity<List<Flight>>(flightService.findByFlightName(flightName), HttpStatus.OK);
	}

	@GetMapping("/getAllFlightSchedule")
	public ResponseEntity<List<FlightSchedule>> getAllFlightSchedule() {
		return new ResponseEntity<List<FlightSchedule>>(flightScheduleService.getAllFlightsSchedule(), HttpStatus.OK);
	}

	@GetMapping("/exportFlightDetails")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Flight Details" + currentDateTime + ".xlsx";
        
        response.setHeader(headerKey, headerValue);
         
        List<Flight> listFlight = flightService.getAllFlights();
         
        FlightIO excelExporter = new FlightIO(listFlight);
         
        excelExporter.export(response);    
    } 

	@GetMapping("/exportFlightScheduleDetails")
	public void exportToExcel1(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Flight Schedule Details" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<FlightSchedule> listFlightSchedule = flightScheduleService.getAllFlightsSchedule();

		FlightScheduleIO excelExporter = new FlightScheduleIO(listFlightSchedule);

		excelExporter.export(response);
	}


}
