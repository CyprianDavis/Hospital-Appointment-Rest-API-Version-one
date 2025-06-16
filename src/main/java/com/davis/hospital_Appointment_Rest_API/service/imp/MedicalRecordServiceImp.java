package com.davis.hospital_Appointment_Rest_API.service.imp;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.davis.hospital_Appointment_Rest_API.model.MedicalRecord;
import com.davis.hospital_Appointment_Rest_API.repository.MedicalRecordRepository;
import com.davis.hospital_Appointment_Rest_API.service.MedicalRecordService;
/**
 * @author CYPRIAN DAVIS
 */
@Service
public class MedicalRecordServiceImp implements MedicalRecordService{
	private MedicalRecordRepository medicalRecordRepository;
	@Override
	public List<MedicalRecord> findAll() {
		// TODO Auto-generated method stub
		return medicalRecordRepository.findAll();
	}

	@Override
	public MedicalRecord save(MedicalRecord medicalRecord) {
		// TODO Auto-generated method stub
		//Set Creation date 
		LocalDateTime now = LocalDateTime.now();
		medicalRecord.setRecordDate(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));
		return medicalRecordRepository.save(medicalRecord);
	}

	@Override
	public List<MedicalRecord> searchByDoctor(String doctor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MedicalRecord> searchByPatientName(String patient) {
		// TODO Auto-generated method stub
		return null;
	}

}
