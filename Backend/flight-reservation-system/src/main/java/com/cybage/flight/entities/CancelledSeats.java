package com.cybage.flight.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CancelledSeats {
	@Id
	@GeneratedValue
	private int id;
	private String cancelledSeatNumber;
	@ManyToOne
	@JoinColumn(name="flight_schedule_id", referencedColumnName = "id")
	private FlightSchedule flightSchedule;
	

}
