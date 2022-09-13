package com.cybage.flight.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor 
@NoArgsConstructor
public class Passenger {


@Id
@GeneratedValue
private int passengerId;
private String firstName;
private String lastName;
private String gender;
private byte age;
private String passportNumber;

@ManyToOne
@JoinColumn(name = "booking_id")
private Booking booking;
@OneToOne(mappedBy="passenger",cascade=CascadeType.ALL)
@JsonManagedReference
private Ticket ticket;

@ManyToOne
@JoinColumn(name="user_id",referencedColumnName = "id",nullable = false)
@JsonBackReference
private User user;

}