package com.cybage.flight.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cybage.flight.entity.FeedBack;
import com.cybage.flight.entity.Flight;

import com.cybage.flight.entity.User;



@Repository
public interface FeedbackRepository extends JpaRepository<FeedBack, Integer> {

	
List<FeedBack> findFeedbackByFlight(Flight flight);
List<FeedBack> findFeedbackByUser(User user);

	
}
