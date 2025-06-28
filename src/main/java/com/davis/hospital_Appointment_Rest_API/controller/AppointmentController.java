package com.davis.hospital_Appointment_Rest_API.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davis.hospital_Appointment_Rest_API.model.Appointment;
import com.davis.hospital_Appointment_Rest_API.model.Doctor;
import com.davis.hospital_Appointment_Rest_API.model.Patient;
import com.davis.hospital_Appointment_Rest_API.service.imp.AppointmentServiceImp;
import com.davis.hospital_Appointment_Rest_API.service.imp.DoctorServiceImp;
import com.davis.hospital_Appointment_Rest_API.service.imp.PatientServiceImp;
import com.davis.hospital_Appointment_Rest_API.utils.ApiResponse;

@RestController("/appointment")
public class AppointmentController {	
	private final AppointmentServiceImp appointmentServiceImp;
	
	private final  DoctorServiceImp doctorServiceImp;


	private final PatientServiceImp patientServiceImp;

   public AppointmentController(AppointmentServiceImp appointmentServiceImp,
		   DoctorServiceImp doctorServiceImp,
		   PatientServiceImp patientServiceImp) {
	   this.appointmentServiceImp =appointmentServiceImp;
	   this.doctorServiceImp = doctorServiceImp;
	   this.patientServiceImp = patientServiceImp;
   }
	
	public List<Appointment> getAppointments(){
		return appointmentServiceImp.findAll();
		
	}
	/**
	 * Books an appointment between a doctor and a patient.
	 * <p>
	 * Creates a new appointment record linking the specified doctor and patient.
	 * Validates the existence of both entities before creating the relationship.
	 * </p>
	 * 
	 * @param doctorId the unique identifier of the doctor (must not be null or empty)
	 * @param patientId the unique identifier of the patient (must not be null or empty)
	 * @return ResponseEntity containing either:
	 *         - Success response with created Appointment (HTTP 201) or
	 *         - Error response if IDs are invalid (HTTP 400) or
	 *         - Error response if entities not found (HTTP 404) or
	 *         - Error response for server errors (HTTP 500)
	 * @throws IllegalArgumentException if either ID parameter is null
	 */
	@PostMapping("/{doctorId}/{patientId}")
	public ResponseEntity<?> bookAppointment(
	    @PathVariable String doctorId,
	    @PathVariable String patientId) {
	    
	    try {
	        // Validate input parameters
	        if (doctorId == null || doctorId.isEmpty() || patientId == null || patientId.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(new ApiResponse<>(false, "Both doctorId and patientId are required and must not be empty"));
	        }

	        // Check if doctor exists - FIXED: was using patientId instead of doctorId
	        Optional<Doctor> doctor = doctorServiceImp.findById(doctorId);
	        Optional<Patient> patient = patientServiceImp.findById(patientId);
	        
	        if (doctor.isEmpty() || patient.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(new ApiResponse<>(false, "Doctor or Patient not found with provided IDs"));
	        }

	        // Create and save appointment
	        Appointment appointment = new Appointment();
	        appointment.setDoctor(doctor.get());
	        appointment.setPatient(patient.get());
	        
	        Appointment savedAppointment = appointmentServiceImp.save(appointment);
	        
	        if (savedAppointment != null) {
	            return ResponseEntity.status(HttpStatus.CREATED)
	                .body(new ApiResponse<>(true, "Appointment booked successfully", savedAppointment));
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new ApiResponse<>(false, "Failed to save appointment"));
	        }
	        
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body(new ApiResponse<>(false, "Error booking appointment: " + e.getMessage()));
	    }
	}

}
