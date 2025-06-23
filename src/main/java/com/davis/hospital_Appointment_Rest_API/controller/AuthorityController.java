package com.davis.hospital_Appointment_Rest_API.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.davis.hospital_Appointment_Rest_API.model.Authority;
import com.davis.hospital_Appointment_Rest_API.service.imp.AuthorityServieImp;

@RestController("/userAuthority")
public class AuthorityController {
	
	@Autowired
	private AuthorityServieImp authorityServieImp;

	
	@GetMapping("/authority")
	public List<Authority> getAuthorities(){
		return authorityServieImp.findAll();
	} 
	@PostMapping
	public ResponseEntity<String> addAuthority(@RequestBody Authority authority){
		try {
			if(authority.equals(authorityServieImp.save(authority))) {
				return ResponseEntity.status(HttpStatus.CREATED).
						body("Operation Successful");
			}
		
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).
					body("Authority registration failed");
		}
	}catch (Exception e) {
		// TODO: handle exception
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("An exception occurred: "+e.getMessage());
	}
		}
	@GetMapping("/name")
	public Authority getByName(@PathVariable("name") String name) {
		return authorityServieImp.findByName(name);
		
	}
	
	
	
}

