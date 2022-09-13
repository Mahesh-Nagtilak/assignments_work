package com.cybage.flight.entities;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
class Complaint {

	@Id
	@GeneratedValue
	private int complainId;
	@NotBlank(message = "complain should not be null")
	private String complain;
	
	private  Date complainDate;

	private String adminResponse;

	
	@ManyToOne
	@JoinColumn(name = "user_id" , nullable=false,referencedColumnName = "id")
	private User user;
	
	@ManyToOne
	@JoinColumn( name="flight_schedule_id", nullable=false)
	@JsonBackReference
	private FlightSchedule flightSchedule;
}
