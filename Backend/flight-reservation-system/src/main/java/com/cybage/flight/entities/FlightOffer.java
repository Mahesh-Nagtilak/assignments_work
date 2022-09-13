package com.cybage.flight.entities;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FlightOffer {
	
	@Id
	@GeneratedValue
	private int offerId;
	
	@NotBlank(message = "not null")
	private String offerName;
	
	@NotBlank(message = "not null")
	@Size(min = 6, message = "Offer Description should not be less than 6 characters")
	private String offerDescription;

	@Positive(message = "Discount Rate can not be negative")
	private int discountRate;

}
