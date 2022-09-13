package com.cybage.flight.daos;

import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cybage.flight.entities.Flight;
import com.cybage.flight.entities.FlightSchedule;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String>
{
	//Method to get list of flight schedule by searching by flight name
	public Flight findByFlightNumber(String flightNumber);
		
		//Method to get list of flight schedule by searching by departure day timings	
		@Query("FROM FlightSchedule WHERE departure_time BETWEEN :startTime AND :endTime")
		public List<FlightSchedule> findByDayTiming(@Param("startTime") Time start,@Param("endTime") Time end);

		//Method to search flights by flight name
		public List<Flight> findByFlightName(String flightName);
}
