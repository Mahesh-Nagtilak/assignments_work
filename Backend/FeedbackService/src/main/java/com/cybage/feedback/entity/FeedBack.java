package com.cybage.feedback.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
public class FeedBack {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int feedbackId;

	private String feedback;

	private float rating;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "flight_schedule_id", referencedColumnName = "id")
	private FlightSchedule flightSchedule;
//	
//	@OneToOne
//	@JoinColumn(name="booking_id")
//	private Booking booking;

	public FeedBack() {
		super();
	}

	public FeedBack(int feedbackId, String feedback, float rating, User user, FlightSchedule flightSchedule) {
		super();
		this.feedbackId = feedbackId;
		this.feedback = feedback;
		this.rating = rating;
		this.user = user;
		this.flightSchedule = flightSchedule;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public FlightSchedule getFlightSchedule() {
		return flightSchedule;
	}

	public void setFlightSchedule(FlightSchedule flightSchedule) {
		this.flightSchedule = flightSchedule;
	}

	@Override
	public String toString() {
		return "FeedBack [feedbackId=" + feedbackId + ", feedback=" + feedback + ", rating=" + rating + ", user=" + user
				+ ", flightSchedule=" + flightSchedule + "]";
	}

}
