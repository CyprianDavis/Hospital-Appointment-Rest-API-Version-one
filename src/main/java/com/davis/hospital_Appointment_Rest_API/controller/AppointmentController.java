package com.davis.hospital_Appointment_Rest_API.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.davis.hospital_Appointment_Rest_API.model.Appointment;
import com.davis.hospital_Appointment_Rest_API.model.Doctor;
import com.davis.hospital_Appointment_Rest_API.model.Patient;
import com.davis.hospital_Appointment_Rest_API.service.imp.AppointmentServiceImp;
import com.davis.hospital_Appointment_Rest_API.service.imp.DoctorServiceImp;
import com.davis.hospital_Appointment_Rest_API.service.imp.PatientServiceImp;

@RestController("/appointment")
public class AppointmentController {

    
	@Autowired
	private AppointmentServiceImp appointmentServiceImp;
	@Autowired
	private  DoctorServiceImp doctorServiceImp;

	@Autowired 
	private PatientServiceImp patientServiceImp;

    AppointmentController(DoctorServiceImp doctorServiceImp) {
        this.doctorServiceImp = doctorServiceImp;
    }
	
	public List<Appointment> getAppointments(){
		return appointmentServiceImp.findAll();
		
	}
	public ResponseEntity<String> bookAppointment(@PathVariable("doctorId")String doctorId,
			@PathVariable("patientId")String patientId){
		
		
				return null;
		
		
	}

}
