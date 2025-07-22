package com.davis.hospital_Appointment_Rest_API.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ResponseEntity<ApiResponse<List<PatientDto>>> getPatients(){
		List<PatientDto> patients = patientServiceImp.findAllPatients();
		String message = patients.isEmpty() ? 
	            "No patients found" : 
	            "Patients retrieved successfully";
		return ResponseEntity.ok(ApiResponse.success(message,patients));
	}
	
	
	
	
	
	
	
	
	
	

}
