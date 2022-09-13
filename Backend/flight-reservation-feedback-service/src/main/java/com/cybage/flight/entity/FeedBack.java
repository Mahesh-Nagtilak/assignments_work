package com.cybage.flight.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedBack {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int feedbackId;
	@NotBlank(message = "feedback shoul not be blank")
	private String feedback;
	
	@PositiveOrZero
	private float rating;

	@ManyToOne
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="flight_schedule_id")
	@JsonBackReference
	private FlightSchedule flightSchedule;
//	
//	@OneToOne
//	@JoinColumn(name="booking_id")
//	private Booking booking;

}
