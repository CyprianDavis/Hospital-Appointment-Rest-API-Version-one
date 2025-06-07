package com.davis.hospital_Appointment_Rest_API.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.davis.hospital_Appointment_Rest_API.model.Doctor;


public interface DoctorRepositry extends JpaRepository<Doctor, String>{
	
	
	List<Doctor> findBySpecialization(String specialization);
	
	List<Doctor> findBySurNameOrGivenNameOrOtherName(String surName, String givenName, String otherName);

}
