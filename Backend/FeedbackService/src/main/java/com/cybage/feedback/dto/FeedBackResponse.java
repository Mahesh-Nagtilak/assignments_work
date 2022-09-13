package com.cybage.feedback.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedBackResponse {
	private String feedback;
	private float rating;
	private String source;
	private String destination;
	private Date departureDate;
	private String flightNumber;
	private String flightName;
	private String flightType;
	private String firstName;
	private String lastName;
	private String email;

}
