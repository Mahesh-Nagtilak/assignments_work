package com.cybage.flight.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cybage.flight.entities.FlightOffer;

@Repository
public interface FlightOfferRepository extends JpaRepository<FlightOffer, Integer> {

	
	Optional<FlightOffer> findByOfferName(String offerName);

	
}
