package com.davis.hospital_Appointment_Rest_API.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.davis.hospital_Appointment_Rest_API.model.Authority;
import com.davis.hospital_Appointment_Rest_API.service.imp.AuthorityServieImp;
import com.davis.hospital_Appointment_Rest_API.utils.ApiResponse;

@RestController("/userAuthorities")
public class AuthorityController {
	
	private final AuthorityServieImp authorityServieImp;
	
	public AuthorityController(AuthorityServieImp authorityServieImp) {
		this.authorityServieImp = authorityServieImp;
	}
	@GetMapping
	public ResponseEntity<ApiResponse<List<Authority>>> getAuthorities(){
		try {
			List<Authority>authorities = authorityServieImp.findAll();
			String message = authorities.isEmpty() ?
					"No Authorities found" :
						"Authorities retrieved Successfully";
			return ResponseEntity.ok(ApiResponse.success(message,authorities));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to retrieve User Roles: "+e.getMessage()));
		}
	}
	
	
	@PostMapping
	public ResponseEntity<?> addAuthority(@RequestBody Authority authority){
		try {
			Authority savedAuthority = authorityServieImp.save(authority);
			if(savedAuthority != null) {
				 // Success case - return 201 Created with success message
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ApiResponse<>(true, "Authority registered successfully", savedAuthority));
			}else {
				 // Failure case - return 400 Bad Request
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>(false, "Role registration failed"));
			}
			}catch(Exception e) {
				 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                    .body(new ApiResponse<>(false, "Error: " + e.getMessage()));
			}
		}
	
		
	@GetMapping("/authority/{name}")
	public ResponseEntity<ApiResponse<Authority>> getByName(@PathVariable("name") String name) {
		try {
			Authority authority = authorityServieImp.findByName(name);
			if(authority != null) {
                return ResponseEntity.ok(ApiResponse.success("Authority found", authority));
			}else {
				 return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                        .body(ApiResponse.error("Authority with name '" + name + "' not found"));
			}
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error searching for role: " + e.getMessage()));
		}
		
	}
	
	
	
}

