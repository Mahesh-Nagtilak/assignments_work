package com.cybage.feedback.dto;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.cybage.feedback.entity.FeedBack;


public class FeedbackModel {
	private int feedbackId;
	private String feedback;
	private float rating;
	private int flightId;
	private int userId;

	public FeedbackModel() {
		super();
	}

	public FeedbackModel(int feedbackId, String feedback, float rating, int flightId, int userId) {
		super();
		this.feedbackId = feedbackId;
		this.feedback = feedback;
		this.rating = rating;
		this.flightId = flightId;
		this.userId = userId;
	}
	
	public FeedBack toFeedbackEntity(FeedbackModel feedbackModel) {
		FeedBack feedBack = new FeedBack();
		BeanUtils.copyProperties(feedbackModel, feedBack);
		return feedBack;
	}
	public int getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	

	@Override
	public String toString() {
		return "FeedbackModel [feedbackId=" + feedbackId + ", feedback=" + feedback + ", rating=" + rating
				+ ", flightId=" + flightId + ", userId=" + userId + "]";
	}

}
