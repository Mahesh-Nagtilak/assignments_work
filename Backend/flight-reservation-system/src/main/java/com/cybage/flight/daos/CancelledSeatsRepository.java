package com.cybage.flight.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cybage.flight.entities.CancelledSeats;
import com.cybage.flight.entities.FlightSchedule;

public interface CancelledSeatsRepository extends JpaRepository<CancelledSeats, Integer> {

	Optional<CancelledSeats> findTopByflightScheduleIdOrderByIdAsc(int flightScheduleId);

	
	

}
