package com.davis.hospital_Appointment_Rest_API.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.davis.hospital_Appointment_Rest_API.model.Patient;
import com.davis.hospital_Appointment_Rest_API.repository.PatientRepository;
import com.davis.hospital_Appointment_Rest_API.service.PatientService;
import com.davis.hospital_Appointment_Rest_API.service.Service;
/**
 * @author CYPRIAN DAVIS
 */
public class PatientServiceImp implements PatientService,Service<Patient>{
	@Autowired
	private PatientRepository patientRepository;
	@Override
	public List<Patient> findAll() {
		// TODO Auto-generated method stub
		return patientRepository.findAll();
	}

	@Override
	public Patient save(Patient patient) {
		// TODO Auto-generated method stub
		return patientRepository.save(patient);
	}

	@Override
	public List<Patient> searchByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
