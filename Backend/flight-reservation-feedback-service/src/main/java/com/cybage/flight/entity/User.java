package com.cybage.flight.entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	
	@Id
    @GeneratedValue
    private int id;
	@NotBlank
    private String firstName;
	@NotBlank
    private String lastName;
	@Email
    private String email;
	@NotBlank
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole UserRole;
    private boolean isLocked = false;
    private boolean isEnabled = false;
  

    
}
