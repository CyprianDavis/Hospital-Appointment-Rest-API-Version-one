package com.davis.hospital_Appointment_Rest_API.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.davis.hospital_Appointment_Rest_API.config.ProjectSecurityConfig;
import com.davis.hospital_Appointment_Rest_API.model.Role;
import com.davis.hospital_Appointment_Rest_API.service.imp.RoleServiceImp;
import com.davis.hospital_Appointment_Rest_API.utils.ApiResponse;

@RestController
@RequestMapping("/api/userRoles")
public class RolesController {

    
	
	private final RoleServiceImp roleServiceImp;
	
	public RolesController(RoleServiceImp roleServiceImp) {
		this.roleServiceImp = roleServiceImp;
	}
	
	public ResponseEntity<ApiResponse<List<Role>>> getRoles(){
		try {
			List<Role> roles = roleServiceImp.findAll();
			
			String message = roles.isEmpty() ?
					"No Roles found" :
						"Roles retrieved successfully";
			return ResponseEntity.ok(ApiResponse.success(message,roles));
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(ApiResponse.error("Failed to retrieve User Roles: "+e.getMessage()));	
		}
	}
	@PostMapping("/role")
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
	@GetMapping("/roleName")
	public Role getByName(@PathVariable("name")String name) {
		return roleServiceImp.findByName(name);
		
	}
	

}
