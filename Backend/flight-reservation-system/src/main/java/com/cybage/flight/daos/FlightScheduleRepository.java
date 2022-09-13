package com.cybage.flight.daos;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cybage.flight.entities.FlightSchedule;

@Repository
public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Integer> {

	List<FlightSchedule> findAllBySourceAndDestinationAndDepartureDateAndFlightFlightTypeAndAvailableSeatsGreaterThanEqual(
			String source, String destination, Date departureDate, String flightType, int availableSeats);

	
	public FlightSchedule findById(int id);
	
	public void deleteById(int id);
	
	public boolean existsById(int id);
}
