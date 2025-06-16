package com.davis.hospital_Appointment_Rest_API.service.imp;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davis.hospital_Appointment_Rest_API.model.Doctor;
import com.davis.hospital_Appointment_Rest_API.repository.DoctorRepository;
import com.davis.hospital_Appointment_Rest_API.service.DoctorService;
/**
 * @author CYPRIAN DAVIS
 */
@Service
public class DoctorServiceImp implements DoctorService{
	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public List<Doctor> findAll() {
		// TODO Auto-generated method stub
		return doctorRepository.findAll();
	}

	@Override
	public Doctor save(Doctor doctor) {
		// TODO Auto-generated method stub
		return doctorRepository.save(doctor);
	}

	@Override
	public List<Doctor> searchBySpecialization(String specialization) {
		// TODO Auto-generated method stub
		return doctorRepository.findBySpecialization(specialization);
	}

	@Override
	public List<Doctor> searchByNames(String names) {
		// TODO Auto-generated method stub
		
		
		
		return null;
	}

}
