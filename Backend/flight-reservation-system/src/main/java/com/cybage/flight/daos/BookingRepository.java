package com.cybage.flight.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cybage.flight.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{

	
	@Query(value = "select * from booking where user_id=?1  order by booked_at desc limit 1",nativeQuery = true)
	Booking getBookingByUserId(int id);

}
