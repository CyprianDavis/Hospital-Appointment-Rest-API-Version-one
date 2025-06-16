package com.davis.hospital_Appointment_Rest_API.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.davis.hospital_Appointment_Rest_API.model.User;
import com.davis.hospital_Appointment_Rest_API.service.imp.UserServiceImp;

@RestController
public class UserController {
	@Autowired
	private UserServiceImp userServiceImp;
	@PostMapping("/signUp")
	public ResponseEntity<String> signUp(@RequestBody User user){
		try {
			if(user.equals(userServiceImp.save(user))){
				return ResponseEntity.status(HttpStatus.CREATED).
						body("Operation Successful");
			}
			else {
				 return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                         body("User registration failed");
				
			}
			
		}
		catch (Exception e) {
			
			// TODO: handle exception
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
	                    body("An exception occurred: " + e.getMessage());
		}
	}
	
	

}
