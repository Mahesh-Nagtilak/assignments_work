package com.cybage.flight.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackModel {
	
	private String feedback;
	private float rating;
	private int flightId;
	private int userId;

	
}
