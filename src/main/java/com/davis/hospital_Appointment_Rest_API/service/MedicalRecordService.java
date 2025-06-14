package com.davis.hospital_Appointment_Rest_API.service;

import java.util.List;

import com.davis.hospital_Appointment_Rest_API.model.MedicalRecord;
/**
 * @author CYPRIAN DAVIS
 */

public interface MedicalRecordService extends Service<MedicalRecord, String>{
	
	List<MedicalRecord> searchByDoctor(String doctor);
	
	List<MedicalRecord> searchByPatientName(String patient);

}
