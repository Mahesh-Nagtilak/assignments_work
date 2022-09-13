package com.cybage.flight.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.flight.daos.TicketRepository;
import com.cybage.flight.dtos.PassengerDTO;
import com.cybage.flight.dtos.TicketDto;
import com.cybage.flight.dtos.UserTicketsDto;
import com.cybage.flight.services.PasssengerService;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/passenger")
public class PassengerController {
	  
	@Autowired
	private PasssengerService passsengerService;
	
	@PostMapping("/addPassenger")
	public ResponseEntity<?> addPassenger(@RequestBody PassengerDTO passengerDTO)
	{
		passsengerService.addPassenger(passengerDTO);
		return ResponseEntity.ok("Passenger added succesfully");
	}
	@GetMapping("/getAllPassengerbyUser/{userId}")
	public ResponseEntity<?> getAllPassengerbyUser(@PathVariable int userId)
	{
		
		return ResponseEntity.ok(passsengerService.getAllPassengerbyUser(userId));
	}
	@GetMapping("/getAdultPassengerbyUser/{userId}")
	public ResponseEntity<?> getAdultPassengerbyUser(@PathVariable int userId)
	{
		System.out.println("adult count");
		int count =passsengerService.getAdultPassengerbyUser(userId);
		System.out.println(count);
		return ResponseEntity.ok(passsengerService.getAdultPassengerbyUser(userId));
	}
	@GetMapping("/getChildrenPassengerbyUser/{userId}")
	public ResponseEntity<?> getChildrenPassengerbyUser(@PathVariable int userId)
	{
		System.out.println("children count");
		return ResponseEntity.ok(passsengerService.getChildrenPassengerbyUser(userId));
	}
	@GetMapping("/getInfantPassengerbyUser/{userId}")
	public ResponseEntity<?> getInfantPassengerbyUser(@PathVariable int userId)
	{
		System.out.println("infant count");
		return ResponseEntity.ok(passsengerService.getInfantPassengerbyUser(userId));
	}
   
	@PostMapping("/bookTicket")
	public ResponseEntity<?> bookTicket( @RequestBody TicketDto ticketDto)

	{      
			passsengerService.bookTicket(ticketDto);
		return ResponseEntity.ok("Ticket booked succesfully");
	}
	
	@DeleteMapping("/cancelTicket/{passengerId}")
	public ResponseEntity<?> cancelTicket(@PathVariable int passengerId )
	{   
		passsengerService.cancelTicket(passengerId);
		return ResponseEntity.ok("Ticket Canceled succesfully");
	}
	
	@DeleteMapping("/deletePassenger/{passengerId}")
	public ResponseEntity<?> deletePassenger(@PathVariable int passengerId )
	{  
		passsengerService.deletePassenger(passengerId);
		return ResponseEntity.ok("Removed succesfully");
	}
	
	@GetMapping("/getAllTickets/{userId}")
	public ResponseEntity<?> getAllTickets(@PathVariable int userId )
	{  
		
		return ResponseEntity.ok(passsengerService.getAllTickets(userId));
	}
	
	
	
	@GetMapping("/getCurrentBookedTicket/{userId}")
	public ResponseEntity<?> getCurrentBookedTicket(@PathVariable int userId )
	{  
		
		return ResponseEntity.ok(passsengerService.getCurrentBookedTicket(userId));
	}
	@GetMapping("/getOldTickets/{userId}")
	public ResponseEntity<?> getOldTicket(@PathVariable int userId )
	{  
		
		return ResponseEntity.ok(passsengerService.getOldTicket(userId));
	}
	
	@GetMapping("/getUserTickets/{userId}")
	public ResponseEntity<?> getUserTickets(@PathVariable int userId )
	{  
		return ResponseEntity.ok(passsengerService.getUserTickets(userId));
	}
	
	
}
