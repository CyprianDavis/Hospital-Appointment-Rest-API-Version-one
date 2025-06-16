package com.davis.hospital_Appointment_Rest_API.service.imp;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.davis.hospital_Appointment_Rest_API.model.Appointment;
import com.davis.hospital_Appointment_Rest_API.repository.AppointmentRepository;
import com.davis.hospital_Appointment_Rest_API.service.AppointmentService;
import com.davis.hospital_Appointment_Rest_API.service.Service;
/**
 * @author CYPRIAN DAVIS
 */
public class AppointmentServiceImp implements AppointmentService,Service<Appointment> {
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Override
	public List<Appointment> findAll() {
		// TODO Auto-generated method stub
		return appointmentRepository.findAll();
	}

	@Override
	public Appointment save(Appointment appointment) {
		// TODO Auto-generated method stub
		//set Creation date and status
		LocalDateTime now = LocalDateTime.now();
		appointment.setCreatedOn(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));
		appointment.setStatus("Scheduled");
		return appointmentRepository.save(appointment);
	}

	@Override
	public List<Appointment> searchByPatientName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Appointment> searchByDoctorName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
