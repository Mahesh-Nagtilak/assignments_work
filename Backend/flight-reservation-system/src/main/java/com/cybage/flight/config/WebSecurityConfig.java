package com.cybage.flight.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  implements WebMvcConfigurer{
    
	

    @Autowired
	private CustomJwtAuthenticationFilter customJwtAuthenticationFilter;
    @Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
    
    @Override
     protected void configure(HttpSecurity http) throws Exception {
       //Start chain for restricting access.
       http.csrf().disable().authorizeRequests()
//       .and()
       //new line
//       .cors().disable().authorizeRequests()
         //The following paths…
         .antMatchers("/api/login/**", "/api/registration/**","/api/passenger/**","/api/flight/**","/api/flightOffer/**","/api/admin/**","/api/userProfile/**","/api/search/**")
           // …are accessible to all users (authenticated or not).
           .permitAll()
         //These paths…
         .antMatchers()
           // …are only available to users with ADMIN role.
           .hasRole("ADMIN")
           .antMatchers()
           // …are only available to users with ADMIN role.
           .hasAnyRole("USER","ADMIN")
         // All remaining paths…
        .anyRequest()
          // ...require user to at least be authenticated
          .authenticated()
        .and()
          // If user isn't authorized to access a path...
          .exceptionHandling()
            // ...redirect them to /403
            .accessDeniedPage("/403").authenticationEntryPoint(jwtAuthenticationEntryPoint).
        	and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
        	and().addFilterBefore(customJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
       
       
  
     }
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowedOrigins("http://localhost:3000","http://localhost:8080");
        WebMvcConfigurer.super.addCorsMappings(registry);
    }
    
  
}
