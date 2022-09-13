package com.cybage.flight.daos;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cybage.flight.entities.Booking;
import com.cybage.flight.entities.Passenger;

@Repository
public interface PasssengerRepository  extends JpaRepository<Passenger,Integer>{

	List<Passenger> findByuserId(int userId);

	@Query(value="select * from passenger where user_id=?1 and age>2 and passenger_id not in (select passenger_id from ticket)  limit 1",nativeQuery = true)
	Passenger getSeatNotAllocatedPassenger(int userId);

	
	@Query(value="select count(*) from  passenger where user_id=?1 and age>2 and passenger_id not in (select passenger_id from ticket)",nativeQuery = true)
	int getSeatNotAllocatedPassengerCount(int userId);

	@Transactional
	@Modifying
	@Query(value = "delete from passenger where passenger_id=?1",nativeQuery = true)
	void deletePassenger(int passengerId);

//	@Query(value="select * from passenger where user_id=?1 and  and passenger_id not in (select passenger_id from ticket) ",nativeQuery = true)
//	List<Passenger> getAllSeatNotAllocatedPassenger(int userId);
	@Query(value="select * from passenger where user_id=?1 and booking_id is null ",nativeQuery = true)
	List<Passenger> getAllSeatNotAllocatedPassenger(int userId);

	
	@Query(value="select count(*) from passenger where user_id=?1 and age>18 and passenger_id not in (select passenger_id from ticket)",nativeQuery = true)
	int getAdultPassengerbyUser(int userId);

	@Query(value="select count(*) from passenger where user_id=?1 and age<=18 and age>2 and passenger_id not in (select passenger_id from ticket)",nativeQuery = true)
	int getChildrenPassengerbyUser(int userId);
	
	@Query(value="select count(*) from passenger where user_id=?1 and age<=2 and booking_id is null",nativeQuery = true)
	int getInfantPassengerbyUser(int userId);

	
	List<Passenger> findAllByBookingBookingIdAndAgeLessThanEqual(Long bookingId, byte age);

	Passenger findByPassengerId(int passengerId);

	@Query(value="select * from passenger where user_id=?1 and age<=2 and booking_id is null",nativeQuery = true)
	List<Passenger> getNotBookedInfantPassengerbyUser(int userId);

	@Query(value="select * from majorproject.passenger where user_id=1 and age<=2 and booking_id  is not null",nativeQuery = true)
	List<Passenger> getInfantsPassengers(int userId);

	
	
}
