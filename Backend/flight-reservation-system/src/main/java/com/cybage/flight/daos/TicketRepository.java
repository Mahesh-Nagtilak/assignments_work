package com.cybage.flight.daos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cybage.flight.entities.FlightSchedule;
import com.cybage.flight.entities.Ticket;


public interface TicketRepository extends JpaRepository<Ticket, Long> {

	Optional<Ticket> findBypassengerPassengerId(int passengerId);

	
	List<Ticket> findBybookingBookingId(Long bookingId);

	@Query(value="select * from ticket where   user_id=?1 and flight_schedule_id not in (select id from flight_schedule where departure_date < CURDATE())",nativeQuery =true)
	List<Ticket> getOldTickets(int userId);
	
//	@Query(value="select * from ticket where   user_id= 1 and flight_schedule_id  in (select id from flight_schedule where departure_date < CURDATE())",nativeQuery =true)
//	List<Ticket> getUpcomingTickets(int userId);
//	
	@Query(value="select * from ticket where   user_id=?1 ",nativeQuery =true)
	List<Ticket> getTickets(int userId);
	
//and flight_schedule_id  in (select id from flight_schedule )

	@Query(value="select flight_schedule_id from ticket where booking_id=?1 limit 1",nativeQuery =true)
	Integer getFlightScheduleId(Long bookingId);


	List<Ticket> findByUserId(int userId);
	
	
	
	
	
	

}
