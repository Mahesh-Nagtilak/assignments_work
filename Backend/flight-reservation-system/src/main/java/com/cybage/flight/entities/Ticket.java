package com.cybage.flight.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
	@Id
	@GeneratedValue
	private Long ticketPrn; 
	
	private String seatNumber;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JsonBackReference
	@JoinColumn(name="booking_id", referencedColumnName = "bookingId")
	private Booking booking;
	
	@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="passenger_id")
	@JsonBackReference
	private Passenger passenger;
	
	@ManyToOne
	@JoinColumn(name="user_id" ,referencedColumnName = "id")
	private User user;

	@ManyToOne//(fetch = FetchType.EAGER)
	@JoinColumn(name="flightSchedule_id")
	private FlightSchedule flightSchedule;
	
	
	
}
