package com.cybage.feedback.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.feedback.dto.FeedBackResponse;
import com.cybage.feedback.dto.FeedbackModel;
import com.cybage.feedback.entity.FeedBack;
import com.cybage.feedback.entity.Flight;
import com.cybage.feedback.entity.FlightSchedule;
import com.cybage.feedback.entity.User;
import com.cybage.feedback.repository.FeedbackRepository;
import com.cybage.feedback.repository.FlightScheduleRepository;
import com.cybage.feedback.repository.UserRepository;

@Service
public class FeedbackService {

	@Autowired
	FeedbackRepository feedbackRepository;
	@Autowired
	FlightScheduleRepository flightRepository;

	@Autowired
	UserRepository userRepository;

	public FeedBack saveFeedback(FeedbackModel feedbackModel) {
		
		FeedBack feedBack = new FeedBack();
		
	String userFeedback=feedbackModel.getFeedback();
	
	float rating=	feedbackModel.getRating();
		
		
		FlightSchedule flightSchedule = flightRepository.findById(feedbackModel.getFlightId());
		User user = userRepository.findById(feedbackModel.getUserId());
		
		feedBack.getRating();
	
		//feedBack = feedbackModel.toFeedbackEntity(feedbackModel);
		feedBack.setFlightSchedule(flightSchedule);
		feedBack.setUser(user);
		feedBack.setFeedback(userFeedback);
		feedBack.setRating(rating);
		System.out.println(feedBack);
		return feedbackRepository.save(feedBack);
		//return feedBack;
	}

	public List<FeedBackResponse> findAllFeedback() {
		
		List<FeedBackResponse> backResponses=new ArrayList<>();
		List<FeedBack> feedbacks= feedbackRepository.findAll();
		for(FeedBack feedBack:feedbacks)
		{FeedBackResponse backResponse=new FeedBackResponse();
			backResponse.setEmail(feedBack.getUser().getEmail());
			backResponse.setFirstName(feedBack.getUser().getFirstName());
			backResponse.setLastName(feedBack.getUser().getLastName());
			backResponse.setDepartureDate(feedBack.getFlightSchedule().getDepartureDate());
			backResponse.setSource(feedBack.getFlightSchedule().getSource());
			backResponse.setDestination(feedBack.getFlightSchedule().getDestination());
			backResponse.setFeedback(feedBack.getFeedback());
			backResponse.setRating(feedBack.getRating());
			backResponse.setFlightNumber(feedBack.getFlightSchedule().getFlight().getFlightNumber());
			backResponse.setFlightName(feedBack.getFlightSchedule().getFlight().getFlightName());
			backResponse.setFlightType(feedBack.getFlightSchedule().getFlight().getFlightType());
			backResponses.add(backResponse);
		
		}
		return backResponses;
		
	}

//
//	public List<FeedBack> getFeedbackByUserId(int userId) {
//
//		return feedbackRepository.findFeedbackByUser(userRepository.getById(userId));
//	}
//
//	public List<FeedBack> getFeedbackByFlightId(int flightId) {
//
//		return feedbackRepository.findFeedbackByFlight(flightRepository.getById(flightId));
//	}
//
	public boolean deleteFlightFeedBackById(int feedbackId) {
		if(findFeedbackById(feedbackId) !=null) {
		 feedbackRepository.deleteById(feedbackId);
		 return true;
		}
		return false;
	}
//
////	public FlightFeedBack getFlightFeedBackById(int feedbackId) {
//
//		return feedbackRepository.findById(feedbackId).orElse(null);
//	}
public FeedBack findFeedbackById(int feedbackId) {
	return feedbackRepository.findFeedbackByFeedbackId(feedbackId);
}
}
