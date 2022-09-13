package com.cybage.feedback.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Flight {

	@Id
	private String flightNumber;

	private String flightName;

	private String flightType;

	private int totalSeats;

	public Flight() {
		super();
	}

	public Flight(String flightNumber, String flightName, String flightType, int totalSeats) {
		super();
		this.flightNumber = flightNumber;
		this.flightName = flightName;
		this.flightType = flightType;
		this.totalSeats = totalSeats;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getFlightType() {
		return flightType;
	}

	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	@Override
	public String toString() {
		return "Flight [flightNumber=" + flightNumber + ", flightName=" + flightName + ", flightType=" + flightType
				+ ", totalSeats=" + totalSeats + "]";
	}

}
