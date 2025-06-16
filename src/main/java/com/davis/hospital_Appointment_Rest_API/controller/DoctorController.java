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

import com.davis.hospital_Appointment_Rest_API.model.Doctor;
import com.davis.hospital_Appointment_Rest_API.service.imp.DoctorServiceImp;

@RestController
public class DoctorController {
	@Autowired
	private DoctorServiceImp doctorServiceImp;
	@PostMapping("/doctor")
	public ResponseEntity<String> addDoctor(@RequestBody Doctor doctor){
		try {
			if(doctor.equals(doctorServiceImp.save(doctor))) {
				return ResponseEntity.status(HttpStatus.CREATED).
						body("Operation Successful");
			}
			else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Doctor Registration Failed");
			}
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An exception occurred: "+e.getMessage());
		}
	}
	 @GetMapping
	public List<Doctor> findAll(){
		return doctorServiceImp.findAll();
	}
	@GetMapping("/specialization")
	public List<Doctor> getBySpecialization(@PathVariable("specialization") String specialization){
		return doctorServiceImp.searchBySpecialization(specialization);
	}
	@GetMapping("/name")
	public List<Doctor> getDoctorByName(@PathVariable("name") String names){
		return doctorServiceImp.searchByNames(names);
		
	}

}
