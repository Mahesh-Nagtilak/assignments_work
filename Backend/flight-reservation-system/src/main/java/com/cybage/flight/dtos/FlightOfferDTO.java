package com.cybage.flight.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightOfferDTO {
	
	private int offerId;
	private String offerName;
	private String offerDescription;
	private int discountRate;

	
	
}
