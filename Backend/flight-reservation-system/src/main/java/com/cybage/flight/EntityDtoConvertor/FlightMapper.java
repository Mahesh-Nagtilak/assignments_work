package com.cybage.flight.EntityDtoConvertor;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cybage.flight.dtos.FlightAvalaibiltyDTO;
import com.cybage.flight.dtos.FlightSchedulDTO;
import com.cybage.flight.dtos.FlightSearchDTO;
import com.cybage.flight.entities.FlightSchedule;

@Component
public class FlightMapper {
	@Autowired
	ModelMapper mapper ;


	public FlightSearchDTO toFlightSearchDto(FlightSchedule flightSchedule) {
		FlightSearchDTO flightSearchDto = mapper.map(flightSchedule, FlightSearchDTO.class);

		return flightSearchDto;
	}

	public FlightAvalaibiltyDTO toFlightAvalaibiltyDto(FlightSchedule flightSchedule) {

		FlightAvalaibiltyDTO avalaibiltyDto = mapper.map(flightSchedule, FlightAvalaibiltyDTO.class);
		return avalaibiltyDto;
	}


	public FlightSchedule toFlightScheduleEntity(FlightSearchDTO flightSearchDto) {
		FlightSchedule flightSchedule = mapper.map(flightSearchDto, FlightSchedule.class);
		return flightSchedule;

	}

	public List<FlightAvalaibiltyDTO> toFlightAvalaibiltyDto(List<FlightSchedule> flightSchedulesList) {

		return flightSchedulesList.stream().map(x -> toFlightAvalaibiltyDto(x)).collect(Collectors.toList());
	}
	
	public FlightSchedulDTO toFlightScheduleDTO(FlightSchedule flightSchedule) {
		FlightSchedulDTO flightScheduleDTO = mapper.map(flightSchedule, FlightSchedulDTO.class);
		return flightScheduleDTO;

	}
}
