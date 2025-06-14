package com.davis.hospital_Appointment_Rest_API.service;

import java.util.List;

import com.davis.hospital_Appointment_Rest_API.model.Appointment;
/**
 * @author CYPRIAN DAVIS
 */
public interface AppointmentService  extends Service<Appointment, Long>{
	
	List<Appointment> searchByPatientName(String name);
	List<Appointment> searchByDoctorName(String name);

}
