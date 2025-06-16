package com.davis.hospital_Appointment_Rest_API.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
	@Bean
	SecurityFilterChain deSecurityFilterChain(HttpSecurity http) throws Exception{
		http.csrf(csrfConfig -> csrfConfig.disable());
				
		http.formLogin(withDefaults());
		http.httpBasic(withDefaults());
		return http.build();
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
	}

}
