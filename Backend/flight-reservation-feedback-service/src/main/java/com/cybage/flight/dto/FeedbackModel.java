package com.cybage.flight.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeedbackModel {
	private int feedbackId;
	private String feedback;
	private String rating;
	private int flightId;
	private int userId;

		
}
