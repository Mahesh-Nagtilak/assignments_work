package com.cybage.flight.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String source;
	private String destination;
	//@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date departureDate;
	//@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date arrivalDate;
	private Time departureTime;
	private Time arrivalTime;
	private int availableSeats;
	private int stops;  
	private double fare;
	private Boolean isRefundable;
 
	@ManyToOne()
	@JoinColumn(name="flight_number")
	@JsonBackReference
	private Flight flight;
//	
//	@OneToMany(mappedBy = "flightSchedule",cascade = CascadeType.ALL)
//	@JsonIgnore
//	private List<Ticket> tickets;
//     
//	@OneToMany(mappedBy = "flightSchedule", cascade = CascadeType.ALL)
//	@JsonManagedReference
//	private List<FeedBack> feebackList;
//
//	@OneToMany(mappedBy = "flightSchedule",cascade = CascadeType.ALL)
//	@JsonManagedReference
//   private List<Complaint> complaintList;
// 
//	
}
