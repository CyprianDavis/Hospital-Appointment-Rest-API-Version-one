package com.davis.hospital_Appointment_Rest_API.service;

import java.util.List;

import com.davis.hospital_Appointment_Rest_API.model.Patient;

/**
 * @author CYPRIAN DAVIS
 */
public interface PatientService extends Service<Patient, String>{
	
	List<Patient> searchByName(String name);
	

}
