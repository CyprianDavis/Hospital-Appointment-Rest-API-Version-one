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

import com.davis.hospital_Appointment_Rest_API.model.Role;
import com.davis.hospital_Appointment_Rest_API.service.imp.RoleServiceImp;

@RestController
public class RolesController {
	@Autowired
	private RoleServiceImp roleServiceImp;
	
	@GetMapping("/role")
	public List<Role> getRoles(){
		return roleServiceImp.findAll();
	}
	@PostMapping
	public ResponseEntity<String> addRole(@RequestBody Role role){
		try {
			if(role.equals(roleServiceImp.save(role))){
				return ResponseEntity.status(HttpStatus.CREATED).
						body("Operation Successful");

			}
			else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                        body("Role registration failed");
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("An exception occurred: " + e.getMessage());
		}
		
	}
	@GetMapping
	public Role getByName(@PathVariable("name")String name) {
		return roleServiceImp.findByName(name);
		
	}
	

}
