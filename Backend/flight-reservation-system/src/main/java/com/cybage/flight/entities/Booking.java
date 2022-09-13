package com.cybage.flight.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
	@Id
	@GeneratedValue
    private Long bookingId;
	@Column
	private double amountPaid;
	@Column(nullable = false)
	private LocalDateTime bookedAt=LocalDateTime.now();
    
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;    
	
	@OneToMany(mappedBy = "booking" ,cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Ticket> ticket;
     
}
