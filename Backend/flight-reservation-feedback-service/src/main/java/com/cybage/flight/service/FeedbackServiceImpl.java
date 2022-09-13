package com.cybage.flight.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.flight.entity.FeedBack;
import com.cybage.flight.repository.FeedbackRepository;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	FeedbackRepository feedbackRepository;
	@Autowired
	FlightRepository flightRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public FeedBack saveFeedback(FeedBack flightFeedback) {
		return feedbackRepository.save(flightFeedback);
	}

	@Override
	public List<FeedBack> findAllFeedback() {
		
		return feedbackRepository.findAll();
	}


	@Override
	public List<FeedBack> getFeedbackByUserId(int userId) {

		return feedbackRepository.findFeedbackByUser(userRepository.getById(userId));
	}

	@Override
	public List<FeedBack> getFeedbackByFlightId(int flightId) {

		return feedbackRepository.findFeedbackByFlight(flightRepository.getById(flightId));
	}

	@Override
	public FeedBack deleteFlightFeedBackById(int feedbackId) {
		
		feedbackRepository.deleteById(feedbackId);
		return null;
		
	}

	@Override
	public FlightFeedBack getFlightFeedBackById(int feedbackId) {
		
		return feedbackRepository.findById(feedbackId).orElse(null);
	}

	@Override
	public FeedBack saveFeedback(FeedBack flightFeedback) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FeedBack getFlightFeedBackById(int feedbackId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FeedBack deleteFlightFeedBackById(int feedbackId) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
