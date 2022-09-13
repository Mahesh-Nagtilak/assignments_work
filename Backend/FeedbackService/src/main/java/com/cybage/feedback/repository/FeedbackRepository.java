package com.cybage.feedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cybage.feedback.entity.FeedBack;



@Repository
public interface FeedbackRepository extends JpaRepository<FeedBack, Integer> {
//List<FeedBack> findFeedbackByFlight(Flight flight);
//List<FeedBack> findFeedbackByUser(User user);
public FeedBack findFeedbackByFeedbackId(int feedbackId);
	
}
