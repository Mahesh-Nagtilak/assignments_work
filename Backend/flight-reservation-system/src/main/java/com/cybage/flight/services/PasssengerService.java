package com.cybage.flight.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.cybage.flight.EntityDtoConvertor.PassengerMapper;
import com.cybage.flight.daos.BookingRepository;
import com.cybage.flight.daos.CancelledSeatsRepository;
import com.cybage.flight.daos.FlightScheduleRepository;
import com.cybage.flight.daos.PasssengerRepository;
import com.cybage.flight.daos.TicketRepository;
import com.cybage.flight.daos.UserRepository;
import com.cybage.flight.dtos.PassengerDTO;
import com.cybage.flight.dtos.TicketDto;
import com.cybage.flight.dtos.UserTicketsDto;
import com.cybage.flight.email.EmailSender;
import com.cybage.flight.email.EmailService;
import com.cybage.flight.entities.Booking;
import com.cybage.flight.entities.CancelledSeats;
import com.cybage.flight.entities.FlightSchedule;
import com.cybage.flight.entities.Passenger;
import com.cybage.flight.entities.Ticket;
import com.cybage.flight.entities.User;
import com.cybage.flight.exception.RecordNotFoundException;

@Service
public class PasssengerService {

	@Autowired
	private FlightScheduleRepository flightSechduleRepository;
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private CancelledSeatsRepository cancelledSeatsRepository;
	@Autowired
	private PasssengerRepository passengerRepository;
	@Autowired
	private PassengerMapper passengerMapper;
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EmailService emailService;

	public void addPassenger(PassengerDTO passengerDto) {
		Passenger passsenger = passengerMapper.toPassengerEntity(passengerDto);
		passengerRepository.save(passsenger);
	}
	public List<PassengerDTO> getAllPassengerbyUser(int userId) {
		List<Passenger>  passenger=	passengerRepository.getAllSeatNotAllocatedPassenger(userId);
		return passengerMapper.toPassengerDTO(passenger);
		
	}
	public String bookTicket(TicketDto ticketDto) {
		int counter = 0;
		int flightScheduleId = ticketDto.getFlightId(); // flight schedule id
		FlightSchedule flightSchedule = flightSechduleRepository.findById(flightScheduleId);
	
		
		if(flightSchedule==null)
		{
			throw new RecordNotFoundException("Flight not available");
		}

		String flightClassType = flightSchedule.getFlight().getFlightType();

		int availableSeats = flightSchedule.getAvailableSeats();

		if (availableSeats == 0) {
			throw new RecordNotFoundException("Sorry...No Seats Available Now..Try Different Flight!!");

		}

		int passengerCount = passengerRepository.getSeatNotAllocatedPassengerCount(ticketDto.getUserId());
		String seatNumber = null;
		Ticket ticket = new Ticket();
		if (availableSeats >= passengerCount) {

			Booking booking = new Booking();
			booking.setAmountPaid(ticketDto.getAmountPaid());

			booking.setBookedAt(LocalDateTime.now());

			User user = userRepository.findById(ticketDto.getUserId());
			booking.setUser(user);
			
		
			bookingRepository.save(booking);
			
			//new line 
		List<Passenger> infantList=passengerRepository.getNotBookedInfantPassengerbyUser(ticketDto.getUserId());
		 for(Passenger passenger:infantList)
		 {   passenger.setBooking(booking);
			 passengerRepository.save(passenger);
		 }
		
		while (availableSeats > 0) {
				Passenger passenger = passengerRepository.getSeatNotAllocatedPassenger(ticketDto.getUserId());
				if (passenger == null) {
					break;
				}
			

				CancelledSeats cancelledSeats = cancelledSeatsRepository
						.findTopByflightScheduleIdOrderByIdAsc(flightScheduleId).orElse(null);

				if (cancelledSeats!=null) {
					ticket.setSeatNumber(cancelledSeats.getCancelledSeatNumber());
					ticket.setFlightSchedule(flightSchedule);
					ticket.setPassenger(passenger);
					ticket.setUser(passenger.getUser());
					ticket.setBooking(booking);
					//new change
					passenger.setBooking(booking);
					
					passenger.setTicket(ticket);
					passengerRepository.save(passenger);
					availableSeats--;
					counter++;
					cancelledSeatsRepository.delete(cancelledSeats);
				} else {
                            
					if(availableSeats%6==0)
					seatNumber = flightClassType.substring(0, 1).toUpperCase() + "-" + availableSeats+" (Window-Right Side)";
   
					else if(availableSeats%6==1)
						seatNumber = flightClassType.substring(0, 1).toUpperCase() + "-" + availableSeats+" (Window-left Side)";
					else
					{
						seatNumber = flightClassType.substring(0, 1).toUpperCase() + "-" + availableSeats;
					}
     
					ticket.setSeatNumber(seatNumber);
					ticket.setFlightSchedule(flightSchedule);
					ticket.setPassenger(passenger);
					ticket.setBooking(booking);
					ticket.setUser(passenger.getUser());
					// allocateSeatToPassenger
					passenger.setTicket(ticket);
					//new change
					passenger.setBooking(booking);
					
					passengerRepository.save(passenger);
					availableSeats--;
					counter++;
					
				}

			}
			if (counter != 0) {
				flightSchedule.setAvailableSeats(availableSeats);
				flightSechduleRepository.save(flightSchedule); // update seat count in db

				
			 booking=bookingRepository.getBookingByUserId(user.getId());
			 System.out.println(booking);
				SendTicketToMail(flightScheduleId,user,ticketDto,flightClassType,booking);
				
				return "Ticket Succesfully Booked";
			} else {
				throw new RecordNotFoundException("No Passenger Available");
			}
		} else {
			throw new RecordNotFoundException(passengerCount + " Seats Not Available");
		}

	}

	public void cancelTicket(int passengerId) {
		
		//new line added
	Passenger passenge=passengerRepository.findByPassengerId(passengerId);
	               if(passenge.getAge()<=2)
	               {
	            	   passengerRepository.delete(passenge);
	               }
	               
		Ticket ticket = ticketRepository.findBypassengerPassengerId(passengerId)
				.orElseThrow(() -> new RecordNotFoundException("Ticket was not avaliable for this passenger"));

		CancelledSeats cancelledSeats = new CancelledSeats();
		cancelledSeats.setCancelledSeatNumber(ticket.getSeatNumber());
		cancelledSeats.setFlightSchedule(ticket.getFlightSchedule());

		FlightSchedule flightSchedule = ticket.getFlightSchedule();
		int oldAvailableSeats = flightSchedule.getAvailableSeats();
		int newAvailableSeats = oldAvailableSeats + 1;
		cancelledSeatsRepository.save(cancelledSeats);
		flightSchedule.setAvailableSeats(newAvailableSeats);
		flightSechduleRepository.save(flightSchedule);
		passengerRepository.deleteById(passengerId);
		String subject="Your Ticket has been Cancelled";
		String message=
				              "<table border='1' style='background-color: lemonchiffon;'><th>Flight Number</th>\r\n"
							+ "    <th>Flight Name</th>\r\n"
							+ "    <th>Source</th>\r\n"
							+ "    <th>Destination</td>\r\n"
							+ "    <th>Departure Date</td>\r\n"
							+ "    <th>Departure Time</th>\r\n"
							+ "    <th>Arrival Time</th>\r\n"
							+ "    <th>Flight Class</th>\r\n"
							+ "</tr>\r\n"
							+ "<tr>\r\n"
							
							+ "    <td>"+flightSchedule.getFlight().getFlightNumber()+"</td>\r\n"
							+ "    <td>"+flightSchedule.getFlight().getFlightName()+"</td>\r\n"
							+ "    <td>"+flightSchedule.getSource()+"</td>\r\n"
						
							+ "    <td>"+flightSchedule.getDestination()+"</td>\r\n"
							+ "    <td>"+flightSchedule.getDepartureDate()+"</td>\r\n"
							+ "    <td>"+flightSchedule.getDepartureTime()+"</td>\r\n"
							+ "    <td>"+flightSchedule.getArrivalTime()+"</td>\r\n"
							+ "    <td>"+flightSchedule.getFlight().getFlightType()+"</td>\r\n"
							+ "</table>"+
				"<h3>Passenger Details :<h3/> <table border='1' style='background-color: lemonchiffon;'><tr>"
						+ "  <td>PRN Number</td>"
						+ "	 <td>First Name </td>"
						+ "  <td>Last Name</td>"
						+ "	 <td>Age</td>"
						+ "	  <td>Gender</td>"
						+ "	 <td>Seat Number</td>"
						+ "	 <td>Status</td>"+
				 "</tr>\r\n"
				+ "<tr>\r\n"
				+ "    <td>"+ticket.getTicketPrn()+"</td>\r\n"
				+ "    <td>"+ticket.getPassenger().getFirstName()+"</td>\r\n"
				+ "    <td>"+ticket.getPassenger().getLastName()+"</td>\r\n"
				+ "    <td>"+ticket.getPassenger().getAge()+"</td>\r\n"
				+ "    <td>"+ticket.getPassenger().getGender()+"</td>\r\n"
				+ "    <td>"+ticket.getSeatNumber()+"</td>\r\n"
						+ "	 <td><b>Cancelled<b/></td>"
				+ "</tr></table><br/>Your refundable amount will be be credited in your account shortly..!!<br/> Feel Free to connect with us for any Query <br/>Admin<br/>Mahesh Nagtilak";
		emailService.send(subject, ticket.getUser().getEmail(),message);
	}

	public void SendTicketToMail(int flightScheduleId,User user,TicketDto ticketDto,String flightClassType,Booking booking)
 { 
		FlightSchedule flightSchedule = flightSechduleRepository.findById(flightScheduleId);
		
		if(flightSchedule==null)
		{
			throw new RecordNotFoundException("Flight not available");
		}
	StringBuilder ticketsMsg=new StringBuilder();
	List<Ticket> tickets=ticketRepository.findBybookingBookingId(booking.getBookingId());
	
		for(Ticket ticket:tickets)
			
		{
		
			ticketsMsg.append(
					
				
					 "</tr>\r\n"
					+ "<tr>\r\n"
					+ "    <td>"+ticket.getTicketPrn()+"</td>\r\n"
					+ "    <td>"+ticket.getPassenger().getFirstName()+"</td>\r\n"
					+ "    <td>"+ticket.getPassenger().getLastName()+"</td>\r\n"
					+ "    <td>"+ticket.getPassenger().getAge()+"</td>\r\n"
					+ "    <td>"+ticket.getPassenger().getGender()+"</td>\r\n"
					+ "    <td>"+ticket.getSeatNumber()+"</td>\r\n"
					+ "</tr>");
			
		}
		byte infantAge=2;
	List<Passenger> passengers=  passengerRepository.findAllByBookingBookingIdAndAgeLessThanEqual(booking.getBookingId(),infantAge);
	for(Passenger passenger :passengers)
	{ 
		ticketsMsg.append(

				 "</tr>\r\n"
				+ "<tr>\r\n"
				+ "    <td>Infant-passenger</td>\r\n"
				+ "    <td>"+passenger.getFirstName()+"</td>\r\n"
				+ "    <td>"+passenger.getLastName()+"</td>\r\n"
				+ "    <td>"+passenger.getAge()+"</td>\r\n"
				+ "    <td>"+passenger.getGender()+"</td>\r\n"
				+ "    <td>No Seat</td>\r\n"
				+ "</tr>");
		
	}
	
	String message="<p style='background-color: lemonchiffon;'>Dear Customer, Thanks for booking your Booking,Your Booking Details Are  indicated Below.<br/><b> Flight Booking Id:"+ booking.getBookingId()+"<br/>Flight Name:"+ flightSchedule.getFlight().getFlightName()+"<br/> Flight Number:"+ 	flightSchedule.getFlight().getFlightNumber()+"<br/> Booking Amount: "+booking.getAmountPaid() +"<br/><h3>Flight Details :<h3/>"+
			 "</b><p/><table border='1' style='background-color: lemonchiffon;' ><tr>\r\n"
			
			+ "    <th>Flight Number</th>\r\n"
			+ "    <th>Flight Name</th>\r\n"
			+ "    <th>Source</th>\r\n"
			+ "    <th>Destination</td>\r\n"
			+ "    <th>Departure Date</td>\r\n"
			+ "    <th>Departure Time</th>\r\n"
			+ "    <th>Arrival Time</th>\r\n"
			+ "    <th>Flight Class</th>\r\n"
			+ "</tr>\r\n"
			+ "<tr>\r\n"
			
			+ "    <td>"+flightSchedule.getFlight().getFlightNumber()+"</td>\r\n"
			+ "    <td>"+flightSchedule.getFlight().getFlightName()+"</td>\r\n"
			+ "    <td>"+flightSchedule.getSource()+"</td>\r\n"
		
			+ "    <td>"+flightSchedule.getDestination()+"</td>\r\n"
			+ "    <td>"+flightSchedule.getDepartureDate()+"</td>\r\n"
			+ "    <td>"+flightSchedule.getDepartureTime()+"</td>\r\n"
			+ "    <td>"+flightSchedule.getArrivalTime()+"</td>\r\n"
			+ "    <td>"+flightSchedule.getFlight().getFlightType()+"</td>\r\n"
			+ "</table><hr/> <h3>Passenger Details :<h3/> <table border='1' style='background-color: lemonchiffon;'><tr>"
			+ "  <td>PRN Number</td>"
			+ "	 <td>First Name </td>"
			+ "  <td>Last Name</td>"
			+ "	 <td>Age</td>"
			+ "	  <td>Gender</td>"
			+ "	 <td>Seat Number</td>"+ticketsMsg+"</table>";
	
		String subject="Booking Confirmation: Flight Number: "+	flightSchedule.getFlight().getFlightNumber()  +", "+ LocalDate.now();
    	
    	//emailService.send(subject, user.getEmail(), message);
		emailService.send(subject,ticketDto.getEmail(), message);
	}
	public void deletePassenger(int passengerId) {
		
		 passengerRepository.deletePassenger(passengerId);
		 
		
	}
	public int getAdultPassengerbyUser(int userId) {
		
	int count=passengerRepository.getAdultPassengerbyUser(userId);
	return count;
	}
	public int getChildrenPassengerbyUser(int userId) {
		
		int count= passengerRepository.getChildrenPassengerbyUser(userId);
		return count;
	}
	public int getInfantPassengerbyUser(int userId) {
		
		int count= passengerRepository.getInfantPassengerbyUser(userId);
		return count;
	}
	
	
	public List<UserTicketsDto> getAllTickets(int userId) {
		List<UserTicketsDto> userTicketsDtos=new ArrayList<>();
		
		List<Ticket> tickets=ticketRepository.getTickets(userId);
		List<Passenger> infants=passengerRepository.getInfantsPassengers(userId);
		if(tickets==null)
		{
			throw new RecordNotFoundException("No Bookings!!");
		}
	
		for(Ticket ticket:tickets)
		{     
		FlightSchedule flightSchedule=	ticket.getFlightSchedule();
		UserTicketsDto userTicketsDto=new UserTicketsDto();
			userTicketsDto.setBookingAmount(ticket.getBooking().getAmountPaid());
			userTicketsDto.setFlightScheduleId(flightSchedule.getId());
			userTicketsDto.setBookingId(ticket.getBooking().getBookingId());
			userTicketsDto.setSource(flightSchedule.getSource());
			userTicketsDto.setDestination(flightSchedule.getDestination());
			userTicketsDto.setDepartureDate(flightSchedule.getDepartureDate());
			userTicketsDto.setDepartureTime(flightSchedule.getDepartureTime());
			userTicketsDto.setArrivalTime(flightSchedule.getArrivalTime());
			userTicketsDto.setArrivalDate(flightSchedule.getArrivalDate());
			userTicketsDto.setFlightName(flightSchedule.getFlight().getFlightName());
			userTicketsDto.setFlightNumber(flightSchedule.getFlight().getFlightNumber());
			userTicketsDto.setFlightClass(flightSchedule.getFlight().getFlightType());
			userTicketsDto.setFirstName(ticket.getPassenger().getFirstName());
			userTicketsDto.setLastName(ticket.getPassenger().getLastName());
			userTicketsDto.setAge(ticket.getPassenger().getAge());
			userTicketsDto.setGender(ticket.getPassenger().getGender());
			userTicketsDto.setSeatNumber(ticket.getSeatNumber());
			userTicketsDto.setPrnNumber(ticket.getTicketPrn());
			userTicketsDto.setPassengerId(ticket.getPassenger().getPassengerId());
			userTicketsDtos.add(userTicketsDto);
		}
		if(infants!=null)
		{
			
		for(Passenger infant : infants)
		{
			
		Integer flightScheduleId=ticketRepository.getFlightScheduleId(infant.getBooking().getBookingId());
		if(flightScheduleId!=null) {
			int id=flightScheduleId.intValue();
			FlightSchedule flightSchedule=flightSechduleRepository.findById(id);
		
			
			
		if(flightSchedule!=null)
		{ UserTicketsDto userTicketsDto=new UserTicketsDto();
		userTicketsDto.setBookingId(infant.getBooking().getBookingId());
		userTicketsDto.setFlightScheduleId(flightSchedule.getId());
		userTicketsDto.setSource(flightSchedule.getSource());
		userTicketsDto.setDestination(flightSchedule.getDestination());
		userTicketsDto.setDepartureDate(flightSchedule.getDepartureDate());
		userTicketsDto.setArrivalTime(flightSchedule.getArrivalTime());
		userTicketsDto.setArrivalDate(flightSchedule.getArrivalDate());
		userTicketsDto.setFlightName(flightSchedule.getFlight().getFlightName());
		userTicketsDto.setFlightNumber(flightSchedule.getFlight().getFlightNumber());
		userTicketsDto.setFlightClass(flightSchedule.getFlight().getFlightType());
		//userTicketsDto.setLastName(ticket.getPassenger().getLastName());
		//userTicketsDto.setFirstName(ticket.getPassenger().getLastName());
		userTicketsDto.setDepartureTime(flightSchedule.getDepartureTime());
		userTicketsDtos.add(userTicketsDto);
		}
		}
		}
		}
		return userTicketsDtos;
	}
	public List<UserTicketsDto> getCurrentBookedTicket(int userId) {
		List<UserTicketsDto> userTicketsDtos=new ArrayList<>();
		Booking booking=	bookingRepository.getBookingByUserId(userId);
		List<Ticket> tickets=booking.getTicket();
		UserTicketsDto userTicketsDto=new UserTicketsDto();
		for(Ticket ticket:tickets)
		{     
		FlightSchedule flightSchedule=	ticket.getFlightSchedule();
			
			userTicketsDto.setBookingAmount(ticket.getBooking().getAmountPaid());
		
			userTicketsDto.setBookingId(ticket.getBooking().getBookingId());
			userTicketsDto.setSource(flightSchedule.getSource());
			userTicketsDto.setDestination(flightSchedule.getDestination());
			userTicketsDto.setDepartureDate(flightSchedule.getDepartureDate());
			userTicketsDto.setArrivalTime(flightSchedule.getArrivalTime());
			userTicketsDto.setArrivalDate(flightSchedule.getArrivalDate());
			userTicketsDto.setFlightName(flightSchedule.getFlight().getFlightName());
			userTicketsDto.setFlightNumber(flightSchedule.getFlight().getFlightNumber());
			userTicketsDto.setFlightClass(flightSchedule.getFlight().getFlightType());
			userTicketsDto.setFirstName(ticket.getPassenger().getFirstName());
			userTicketsDto.setFirstName(ticket.getPassenger().getLastName());
			userTicketsDto.setAge(ticket.getPassenger().getAge());
			userTicketsDto.setGender(ticket.getPassenger().getGender());
		
			userTicketsDtos.add(userTicketsDto);
		}
		return userTicketsDtos;
	}
	public List<UserTicketsDto> getOldTicket(int userId) {
		
		List<UserTicketsDto> userTicketsDtos=new ArrayList<>();
		List<Ticket> tickets=ticketRepository.getOldTickets(userId);
		if(tickets==null)
		{
			throw new RecordNotFoundException("No Previous Bookings!!");
		}
		UserTicketsDto userTicketsDto=new UserTicketsDto();
		for(Ticket ticket:tickets)
		{     
		FlightSchedule flightSchedule=	ticket.getFlightSchedule();
			
			userTicketsDto.setBookingAmount(ticket.getBooking().getAmountPaid());
		
			userTicketsDto.setBookingId(ticket.getBooking().getBookingId());
			userTicketsDto.setSource(flightSchedule.getSource());
			userTicketsDto.setDestination(flightSchedule.getDestination());
			userTicketsDto.setDepartureDate(flightSchedule.getDepartureDate());
			userTicketsDto.setArrivalTime(flightSchedule.getArrivalTime());
			userTicketsDto.setArrivalDate(flightSchedule.getArrivalDate());
			userTicketsDto.setFlightName(flightSchedule.getFlight().getFlightName());
			userTicketsDto.setFlightNumber(flightSchedule.getFlight().getFlightNumber());
			userTicketsDto.setFlightClass(flightSchedule.getFlight().getFlightType());
			userTicketsDto.setFirstName(ticket.getPassenger().getFirstName());
			userTicketsDto.setFirstName(ticket.getPassenger().getLastName());
			userTicketsDto.setAge(ticket.getPassenger().getAge());
			userTicketsDto.setGender(ticket.getPassenger().getGender());
		
			userTicketsDtos.add(userTicketsDto);
		}
		return userTicketsDtos;
	}
	public List<UserTicketsDto> getUserTickets(int userId) {
		List<UserTicketsDto> userTicketsDtos=new ArrayList<>();
		
	List<Ticket>tickets=ticketRepository.findByUserId(userId);
	for(Ticket ticket:tickets)
	{UserTicketsDto userTicketsDto=new UserTicketsDto();
		FlightSchedule flightSchedule=	ticket.getFlightSchedule();
		
		userTicketsDto.setBookingAmount(ticket.getBooking().getAmountPaid());
	
		userTicketsDto.setBookingId(ticket.getBooking().getBookingId());
		userTicketsDto.setSource(flightSchedule.getSource());
		userTicketsDto.setDestination(flightSchedule.getDestination());
		userTicketsDto.setDepartureDate(flightSchedule.getDepartureDate());
		userTicketsDto.setArrivalTime(flightSchedule.getArrivalTime());
		userTicketsDto.setArrivalDate(flightSchedule.getArrivalDate());
		userTicketsDto.setFlightName(flightSchedule.getFlight().getFlightName());
		userTicketsDto.setFlightNumber(flightSchedule.getFlight().getFlightNumber());
		userTicketsDto.setFlightClass(flightSchedule.getFlight().getFlightType());
		userTicketsDto.setFirstName(ticket.getPassenger().getFirstName());
		userTicketsDto.setFirstName(ticket.getPassenger().getLastName());
		userTicketsDto.setAge(ticket.getPassenger().getAge());
		userTicketsDto.setGender(ticket.getPassenger().getGender());
	
		userTicketsDtos.add(userTicketsDto);
	
	}
	return userTicketsDtos;
		
		
	}
	

}
