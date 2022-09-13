package com.cybage.flight.entity;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

	 @Id
	private String flightNumber;
	
     @NotBlank(message = "flight name should not be null")
     @Size(min = 4,max = 15, message = "flight should be  min 4 to max 15 character")
	private String flightName;
     
     @NotBlank(message = "flight type should not be null")
	private String flightType;
     
	@Positive(message = "flight should have positive seats")
	private int totalSeats;
	
	
	
	
	
}
