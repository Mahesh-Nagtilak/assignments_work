package com.cybage.flight;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cybage.flight.daos.BookingRepository;
import com.cybage.flight.daos.FlightRepository;
import com.cybage.flight.daos.FlightScheduleRepository;
import com.cybage.flight.daos.UserRepository;
import com.cybage.flight.entities.Flight;
import com.cybage.flight.entities.FlightSchedule;
import com.cybage.flight.entities.User;

@SpringBootTest
class FlightReservationSystemApplicationTests {

	@Test
	void contextLoads() {
	}
    @Autowired
    UserRepository repository;
    
    @Autowired
    BookingRepository bookingRepository;

	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	FlightScheduleRepository flightScheduleRepository;
	
	@Test
	public void testAddFlight() {
		Flight flight = new Flight();
		flight.setFlightNumber("DIS-102");
		flight.setFlightName("Disney");
		flight.setFlightType("Business");
		flight.setTotalSeats(200);
		flightRepository.save(flight);
		assertNotNull(flightRepository.findByFlightNumber("DIS-102").getClass());
		
	}
	
	@Test
	public void testGetAllFlights() {
		List<Flight> flightList = flightRepository.findAll();
		assertThat(flightList).size().isGreaterThan(0);
	}
	
	@Test
	public void testGetAllFlightsSchedule() {
		List<FlightSchedule> findScheduleList = flightScheduleRepository.findAll();
		assertThat(findScheduleList).size().isGreaterThan(0);
	}
		
	@Test
	public void testFlightDetails() {
		Flight flight = flightRepository.findByFlightNumber("AA-101");
		assertEquals(200, flight.getTotalSeats());
	}
	
	@Test
	public void testFlightScheduleDetails() {
		FlightSchedule flightSchedule = flightScheduleRepository.findById(10);
		assertEquals(200, flightSchedule.getAvailableSeats());
	}
	
	@Test
	public void testUpdateFlightDetails() {
		Flight flight = flightRepository.findByFlightNumber("IND-103");
		flight.setFlightName("Vistara");
		flight.setFlightType("Business");
		flight.setTotalSeats(300);
		flightRepository.save(flight);
		assertNotEquals(200, flightRepository.findByFlightNumber("IND-103").getTotalSeats());
	}
	
	@Test
	public void testUpdateFlightScheduleDetails() {
		FlightSchedule flightSchedule = flightScheduleRepository.findById(7);
		flightSchedule.setSource("Delhi");
		flightSchedule.setDestination("Hyderabad");
		flightSchedule.setDepartureDate(Date.valueOf("2022-01-01"));
		flightSchedule.setArrivalDate(Date.valueOf("2022-01-01")); 
		flightSchedule.setDepartureTime(Time.valueOf("07:00:00"));
		flightSchedule.setArrivalTime(Time.valueOf("08:00:00"));
		flightSchedule.setAvailableSeats(198);
		flightSchedule.setStops(1);
		flightSchedule.setFare(3000);
		flightSchedule.setIsRefundable(true);
		flightScheduleRepository.save(flightSchedule);
		assertNotEquals(200, flightScheduleRepository.findById(7).getFare());
		
	}
	
	@Test
	public void testDeleteFlightDetails() {		
		flightRepository.deleteById("VIS-101");
		assertThat(flightRepository.existsById("VIS-101")).isFalse();
	}
	
	@Test
	public void testDeleteFlightScheduleDetails() {
		flightScheduleRepository.deleteById(34);
		assertThat(flightScheduleRepository.existsById(35)).isFalse();
	}
   
    
    @Test
    public void testReadAll() {
                    List<User> list = repository.findAll();
                    assertThat(list).size().isGreaterThan(0);
    }
    
    @Test
    public void testUser() {
                    User user = repository.findById(1);
                    System.out.println(user.getLastName());
                    assertEquals("Patil", user.getLastName());
                    
    }
    
    @Test
    public void testUpdate() {
                    User user = repository.findById(1);
                    user.setEmail("amitpatil@gmail.com");
                    repository.save(user);
                    assertNotEquals("amit@gmail.com", repository.findById(1));
    }

	
}
