package com.davis.hospital_Appointment_Rest_API.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davis.hospital_Appointment_Rest_API.service.imp.PatientServiceImp;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
	private final PatientServiceImp patientServiceImp;
	
	public PatientController(PatientServiceImp patientServiceImp) {
		this.patientServiceImp = patientServiceImp;
	}
	
	
	
	
	
	
	
	
	
	
	

}
