package com.cybage.flight.service;

import java.util.List;

import com.cybage.flight.entity.FeedBack;




public interface FeedbackService {

	List<FeedBack> findAllFeedback();

	FeedBack saveFeedback(FeedBack flightFeedback);
	FeedBack getFlightFeedBackById(int feedbackId);

	List<FeedBack> getFeedbackByFlightId(int flightId);
	
	List<FeedBack> getFeedbackByUserId(int userId);

	FeedBack deleteFlightFeedBackById(int feedbackId);
}
