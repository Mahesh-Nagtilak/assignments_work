package com.cybage.flight;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class FlightReservationSystemApplication {
 
	
	@Bean
	public RestTemplate getRestTemplate()
	{
	  return new RestTemplate();
	}
	 @Bean
	   public ModelMapper modelMapper() {
	       return new ModelMapper();
	    }
	public static void main(String[] args) {
		SpringApplication.run(FlightReservationSystemApplication.class, args);
	}
 


}
