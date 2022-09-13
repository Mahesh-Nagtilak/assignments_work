package com.cybage.flight.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.flight.daos.FlightOfferRepository;
import com.cybage.flight.entities.FlightOffer;
import com.cybage.flight.exception.RecordNotFoundException;

@Service
public class FlightOfferService  {

	@Autowired
	private FlightOfferRepository flightOfferRepository;
	
	
	public void addFlightOffer(FlightOffer flightOffer) {
	
		flightOfferRepository.save(flightOffer);
	}

	
	public List<FlightOffer> getAllFlightOffers() {
		
		return flightOfferRepository.findAll();
	}

	
	public void updateFlightOffer(FlightOffer flightOffer, int flightOfferId) {
		
		FlightOffer oldFlightOffer = flightOfferRepository.findById(flightOfferId).orElse(null);
		
		if(oldFlightOffer == null)
				throw new RecordNotFoundException("No flight to update");
		
		oldFlightOffer.setOfferName(flightOffer.getOfferName());
		oldFlightOffer.setDiscountRate(flightOffer.getDiscountRate());
	
		oldFlightOffer.setOfferDescription(flightOffer.getOfferDescription());
		flightOfferRepository.save(oldFlightOffer);
		
	}

	
	public void deleteFlightOffer(int flightOfferId) {
		
		FlightOffer flightOffer = flightOfferRepository.findById(flightOfferId).orElse(null); 
		
		if(flightOffer == null )
			throw new RecordNotFoundException("No Offer to Delete");
		
		flightOfferRepository.deleteById(flightOfferId);
	}

	
	public FlightOffer getOfferById(int id) {
		
		return flightOfferRepository.findById(id).orElse(null) ;
	}



}











