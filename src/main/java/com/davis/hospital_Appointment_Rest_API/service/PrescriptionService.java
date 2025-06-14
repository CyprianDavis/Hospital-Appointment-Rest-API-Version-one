package com.davis.hospital_Appointment_Rest_API.service;
/**
 * @author CYPRIAN DAVIS
 */

import java.util.List;

import com.davis.hospital_Appointment_Rest_API.model.Prescription;
/**
 * @author CYPRIAN DAVIS
 */

public interface PrescriptionService extends Service<String, Long>{
	
	 List<Prescription> searchByPatient(String name);
	 
	 List<Prescription> searchByDoctor(String name);
}
