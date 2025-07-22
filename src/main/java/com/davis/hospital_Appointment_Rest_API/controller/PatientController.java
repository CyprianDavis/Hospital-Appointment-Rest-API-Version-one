package com.davis.hospital_Appointment_Rest_API.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davis.hospital_Appointment_Rest_API.dto.PatientDto;
import com.davis.hospital_Appointment_Rest_API.service.imp.PatientServiceImp;
import com.davis.hospital_Appointment_Rest_API.utils.ApiResponse;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
	private final PatientServiceImp patientServiceImp;
	
	public PatientController(PatientServiceImp patientServiceImp) {
		this.patientServiceImp = patientServiceImp;
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
	@GetMapping
	public ResponseEntity<ApiResponse<List<PatientDto>>> findAllPatients(){
		List<PatientDto> patients = patientServiceImp.findAllPatients();
		String message = patients.isEmpty() ? 
	            "No patients found" : 
	            "Patients retrieved successfully";
		return ResponseEntity.ok(ApiResponse.success(message,patients));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
	@GetMapping("/{name}")
	public ResponseEntity<?> searchPatientByName(@PathVariable String name){
		try {
			if(name.isEmpty()) {
				  return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		                    .body(new ApiResponse<>(false, "Patient Name is required"));
			}
			List<PatientDto> patients = patientServiceImp.searchPatientByName(name);
			 String message = patients.isEmpty() 
		                ? "No patient found " 
		                : "Patient retrieved successfully";
	            return ResponseEntity.ok(ApiResponse.success(message, patients));


		}catch (Exception e) {
			// TODO: handle exception
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .body(ApiResponse.error("Failed to retrieve Patient by Name: " + e.getMessage()));    
		}
	}
	
	
	
	
	
	
	
	
	
	

}
