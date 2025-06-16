package com.davis.hospital_Appointment_Rest_API.service.imp;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.davis.hospital_Appointment_Rest_API.model.Prescription;
import com.davis.hospital_Appointment_Rest_API.repository.PrescriptionRepository;
import com.davis.hospital_Appointment_Rest_API.service.PrescriptionService;
 
public class PrescriptionServiceImp implements PrescriptionService {
	@Autowired
	private PrescriptionRepository prescriptionRepository;

	@Override
	public List<Prescription> findAll() {
		// TODO Auto-generated method stub
		return prescriptionRepository.findAll();
	}

	@Override
	public Prescription save(Prescription prescription) {
		// TODO Auto-generated method stub
		//Set Creation date 
        LocalDateTime now = LocalDateTime.now();
        prescription.setCreatedOn(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));

		return prescriptionRepository.save(prescription);
	}

	@Override
	public List<Prescription> searchByPatient(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Prescription> searchByDoctor(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
	



	

}
