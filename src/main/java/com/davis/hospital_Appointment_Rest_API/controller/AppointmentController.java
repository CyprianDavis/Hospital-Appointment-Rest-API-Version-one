package com.davis.hospital_Appointment_Rest_API.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davis.hospital_Appointment_Rest_API.model.Appointment;
import com.davis.hospital_Appointment_Rest_API.model.Doctor;
import com.davis.hospital_Appointment_Rest_API.model.Patient;
import com.davis.hospital_Appointment_Rest_API.service.imp.AppointmentServiceImp;
import com.davis.hospital_Appointment_Rest_API.service.imp.DoctorServiceImp;
import com.davis.hospital_Appointment_Rest_API.service.imp.PatientServiceImp;
import com.davis.hospital_Appointment_Rest_API.utils.ApiResponse;

/**
 * REST Controller for managing appointments.
 * <p>
 * Provides endpoints for creating and retrieving appointment records in the system.
 * Handles the relationship between doctors and patients through appointments.
 * </p>
 * 
 * <p><b>Endpoints:</b></p>
 * <ul>
 *   <li>GET /api/appointments - Retrieve all appointments</li>
 *   <li>POST /api/appointments/{doctorId}/{patientId} - Book a new appointment</li>
 * </ul>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-29
 * @see Appointment
 * @see Doctor
 * @see Patient
 */
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    
    private final AppointmentServiceImp appointmentServiceImp;
    private final DoctorServiceImp doctorServiceImp;
    private final PatientServiceImp patientServiceImp;

    /**
     * Constructs a new AppointmentController with required services.
     * 
     * @param appointmentServiceImp service for appointment operations
     * @param doctorServiceImp service for doctor operations
     * @param patientServiceImp service for patient operations
     */
    public AppointmentController(AppointmentServiceImp appointmentServiceImp,
                               DoctorServiceImp doctorServiceImp,
                               PatientServiceImp patientServiceImp) {
        this.appointmentServiceImp = appointmentServiceImp;
        this.doctorServiceImp = doctorServiceImp;
        this.patientServiceImp = patientServiceImp;
    }

    /**
     * Retrieves all appointments from the system.
     * <p>
     * This endpoint returns a list of all existing appointments in the system. 
     * The response includes either:
     * <ul>
     *   <li>A success response with the list of appointments (HTTP 200), or</li>
     *   <li>An empty list with a "No appointments found" message if no records exist, or</li>
     *   <li>An error response if the operation fails (HTTP 500)</li>
     * </ul>
     * </p>
     * 
     * @return ResponseEntity containing:
     *         - ApiResponse with list of appointments and success message (HTTP 200 OK), or
     *         - ApiResponse with empty list and "No appointments found" message (HTTP 200 OK), or
     *         - ApiResponse with error message (HTTP 500 Internal Server Error)
     * @throws DataAccessException if there is an issue accessing the data layer
     * @throws RuntimeException for other unexpected errors during processing
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<Appointment>>> getAppointments() {
        try {
            List<Appointment> appointments = appointmentServiceImp.findAll();
            
            String message = appointments.isEmpty() 
                ? "No appointments found" 
                : "Appointments retrieved successfully";
                
            return ResponseEntity.ok(ApiResponse.success(message, appointments));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Failed to retrieve appointments: " + e.getMessage()));    
        }
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
     *         - Success response with created Appointment (HTTP 201), or
     *         - Error response if IDs are invalid (HTTP 400), or
     *         - Error response if entities not found (HTTP 404), or
     *         - Error response for server errors (HTTP 500)
     * @throws IllegalArgumentException if either ID parameter is null
     */
    @PostMapping("/{doctorId}/{patientId}")
    public ResponseEntity<?> bookAppointment(
        @PathVariable String doctorId,
        @PathVariable String patientId) {
        
        try {
            if (doctorId == null || doctorId.isEmpty() || patientId == null || patientId.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, "Both doctorId and patientId are required and must not be empty"));
            }

            Optional<Doctor> doctor = doctorServiceImp.findById(doctorId);
            Optional<Patient> patient = patientServiceImp.findById(patientId);
            
            if (doctor.isEmpty() || patient.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Doctor or Patient not found with provided IDs"));
            }

            Appointment appointment = new Appointment();
            appointment.setDoctor(doctor.get());
            appointment.setPatient(patient.get());
            
            Appointment savedAppointment = appointmentServiceImp.save(appointment);
            
            if (savedAppointment != null) {
                return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(true, "Appointment booked successfully", savedAppointment));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Failed to book appointment"));
            }
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error booking appointment: " + e.getMessage()));
        }
    }
}