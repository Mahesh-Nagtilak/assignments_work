package com.cybage.flight.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cybage.flight.dtos.FeedbackModel;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/feedback")
public class FeedBackController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/feedback-list")
	public ResponseEntity<FeedbackModel[]> getAllFeedbacks() {

		String url = "http://localhost:8078/api/feedback/feedback-list";

		return  restTemplate.getForEntity(url,FeedbackModel[].class );

	}

	@PostMapping("/add-feedback")
	public ResponseEntity<String> addFeedback(@RequestBody FeedbackModel feedbackModel) {
		String url = "http://localhost:8078/api/feedback/add-feedback";
		
		return (ResponseEntity<String>) restTemplate.postForEntity(url, feedbackModel, String.class);

	}

}
