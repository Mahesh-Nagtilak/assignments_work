package com.cybage.flight.EntityDtoConvertor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cybage.flight.dtos.UserRegistrationDTO;
import com.cybage.flight.entities.User;
@Component
public class UserMapper {
	@Autowired
	ModelMapper mapper ;
	public UserRegistrationDTO toRegistrationRequestDto(User user)
	{    
		UserRegistrationDTO registrationRequestDto=mapper.map(user, UserRegistrationDTO.class);
		return registrationRequestDto;
	
	}
	public User toUserEntity(UserRegistrationDTO requestDto)
	{
		User user=	mapper.map(requestDto, User.class);
		return user;
	}

}
