package com.cybage.flight.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.flight.dto.FeedbackModel;
import com.cybage.flight.entity.Flight;
import com.cybage.flight.entity.FlightFeedBack;
import com.cybage.flight.service.FeedbackService;

@CrossOrigin
@RestController
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	FeedbackService feedbackService;
	@Autowired
	FlightRepository flightRepository;
	@Autowired
	UserRepository userRepository;

	FlightFeedBack flightFeedBack = new FlightFeedBack();
	Flight flight = new Flight();

	 private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	 @GetMapping("/feedback-list")
	public ResponseEntity<List<FeedbackModel>> getAllFeedbacks() throws RecordNotFoundException {

		List<FlightFeedBack> feedbackList = new ArrayList<>();
		feedbackList = feedbackService.findAllFeedback();
		
		if (feedbackList ==null) 
			throw new RecordNotFoundException("feedback list not found");		
		return new ResponseEntity<List<FeedbackModel>>(FeedbackModel.fromEntity(feedbackList), HttpStatus.OK);

	}

	@PostMapping("/add-feedback")
	public ResponseEntity<String> addFeedback(@RequestBody FeedbackModel feedbackModel) {
	   feedbackModel.getUserId();
		flightFeedBack = FeedbackModel.toFlightFeedBackEntity(feedbackModel);
		flightFeedBack.setFlight(flightRepository.getById(feedbackModel.getFlightId()));
		flightFeedBack.setUser(userRepository.getById(feedbackModel.getUserId()));
        feedbackService.saveFeedback(flightFeedBack);
        return new ResponseEntity<String>("feedback added:", HttpStatus.CREATED);

	}

	@DeleteMapping("/delete-feedback/{feedbackId}")
	public ResponseEntity<String> deleteFeedback(@PathVariable int feedbackId) throws RecordNotFoundException {
		FlightFeedBack flightFeedback = feedbackService.deleteFlightFeedBackById(feedbackId);
		if (flightFeedback == null) {
			throw new RecordNotFoundException("feedback id not find to delete");
		}
		feedbackService.deleteFlightFeedBackById(feedbackId);

		return new ResponseEntity<String>("feedback deleted", HttpStatus.OK);

	}

	@PutMapping("/update-feedback/{feedbackId}")
	public ResponseEntity<String> addFeedback(@RequestBody FeedbackModel feedbackModel, @PathVariable int feedbackId) {
       feedbackModel.setFeedbackId(feedbackId);
		flightFeedBack = FeedbackModel.toFlightFeedBackEntity(feedbackModel);
		flightFeedBack.setFlight(flightRepository.getById(feedbackModel.getFlightId()));
		flightFeedBack.setUser(userRepository.getById(feedbackModel.getUserId()));
		feedbackService.saveFeedback(flightFeedBack);
		return new ResponseEntity<String>("feedback updated:", HttpStatus.CREATED);
	}


	
	@GetMapping("/get-feedback/{feedbackId}")
	public ResponseEntity<FlightFeedBack> getFeedbackModel(@PathVariable int feedbackId)
			throws RecordNotFoundException {
		 FlightFeedBack flightfeedback= new FlightFeedBack();
		 flightfeedback= feedbackService.getFlightFeedBackById(feedbackId);
		if (flightfeedback == null) {
			throw new RecordNotFoundException(" feedback is not found for id " + feedbackId);
		}
		return new ResponseEntity<FlightFeedBack>(flightfeedback, HttpStatus.FOUND);
	}

	@GetMapping("/get-feedbacksByFlightId/{flightId}")
	public ResponseEntity<List<FeedbackModel>> getFeedbackByFlightId(@PathVariable int flightId)
			throws RecordNotFoundException {
		
		List<FlightFeedBack> feedbackList = new ArrayList<>();
		System.out.println(flightId);

		if (feedbackList.isEmpty()) {
			throw new RecordNotFoundException("feedback list is not found for the flight id " + flightId);
		}
		
		feedbackList = feedbackService.getFeedbackByFlightId(flightId);
		return new ResponseEntity<List<FeedbackModel>>(FeedbackModel.fromEntity(feedbackList), HttpStatus.OK);
	}

	@GetMapping("/get-feedbackByUser/{userId}")
	public ResponseEntity<List<FeedbackModel>> getFeedbackByUserId(@PathVariable int userId){
			
		List<FlightFeedBack> feedbackList = new ArrayList<>();

		if (feedbackList.isEmpty()) {
			throw new RecordNotFoundException("feedback list is not found for the user id " + userId);
		}
		feedbackList = feedbackService.getFeedbackByUserId(userId);
		return new ResponseEntity<List<FeedbackModel>>(FeedbackModel.fromEntity(feedbackList), HttpStatus.OK);
	}

}
