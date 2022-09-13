package com.cybage.feedback.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class FlightSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String source;
	private String destination;
	// @DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date departureDate;
	// @DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date arrivalDate;
	private Time departureTime;
	private Time arrivalTime;
	private int availableSeats;
	private int stops;
	private double fare;
	private Boolean isRefundable;

	@ManyToOne
	@JoinColumn(name = "flight_number")
	//@JsonBackReference
	private Flight flight;
//	
//	@OneToMany(mappedBy = "flightSchedule",cascade = CascadeType.ALL)
//	@JsonIgnore
//	private List<Ticket> tickets;
////     
//	@OneToMany(mappedBy = "flightSchedule", cascade = CascadeType.ALL)
//	@JsonManagedReference
//	private List<FeedBack> feebackList;
////
//	@OneToMany(mappedBy = "flightSchedule",cascade = CascadeType.ALL)
//	@JsonManagedReference
//   private List<Complaint> complaintList;
// 
//	

	public FlightSchedule() {
		super();
	}

	public FlightSchedule(int id, String source, String destination, Date departureDate, Date arrivalDate,
			Time departureTime, Time arrivalTime, int availableSeats, int stops, double fare, Boolean isRefundable,
			Flight flight) {
		super();
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.availableSeats = availableSeats;
		this.stops = stops;
		this.fare = fare;
		this.isRefundable = isRefundable;
		//this.flight = flight;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Time getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}

	public Time getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public int getStops() {
		return stops;
	}

	public void setStops(int stops) {
		this.stops = stops;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public Boolean getIsRefundable() {
		return isRefundable;
	}

	public void setIsRefundable(Boolean isRefundable) {
		this.isRefundable = isRefundable;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}


}
