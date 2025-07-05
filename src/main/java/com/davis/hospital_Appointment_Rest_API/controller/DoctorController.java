package com.davis.hospital_Appointment_Rest_API.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davis.hospital_Appointment_Rest_API.dto.ViewDoctor;
import com.davis.hospital_Appointment_Rest_API.model.Doctor;
import com.davis.hospital_Appointment_Rest_API.service.imp.DoctorServiceImp;
import com.davis.hospital_Appointment_Rest_API.utils.ApiResponse;

/**
 * REST Controller for managing doctor-related operations.
 * <p>
 * Provides endpoints for retrieving doctor information by various criteria including:
 * <ul>
 *   <li>All doctors</li>
 *   <li>Doctors by specialization</li>
 *   <li>Doctors by name</li>
 * </ul>
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-29
 * @see Doctor
 * @see DoctorServiceImp
 * @see ApiResponse
 */
@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorServiceImp doctorServiceImp;
    
    /**
     * Constructs a new DoctorController with required services.
     * 
     * @param doctorServiceImp service for doctor operations
     */
    public DoctorController(DoctorServiceImp doctorServiceImp) {
        this.doctorServiceImp = doctorServiceImp;
    }
    
    /**
     * Retrieves all doctors from the system.
     * <p>
     * Returns a complete list of all doctors registered in the system.
     * The response includes either:
     * <ul>
     *   <li>A success response with the list of doctors (HTTP 200), or</li>
     *   <li>An empty list with a "No doctors found" message if no records exist, or</li>
     *   <li>An error response if the operation fails (HTTP 500)</li>
     * </ul>
     * </p>
     * 
     * @return ResponseEntity containing:
     *         - ApiResponse with list of doctors and success message (HTTP 200 OK), or
     *         - ApiResponse with empty list and "No doctors found" message (HTTP 200 OK), or
     *         - ApiResponse with error message (HTTP 500 Internal Server Error)
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<ViewDoctor>>> findAll() {
        try {
            List<ViewDoctor> doctors = doctorServiceImp.findAllDoctorsAsViewDoctors();
            String message = doctors.isEmpty() 
                ? "No doctors found" 
                : "Doctors retrieved successfully";
            return ResponseEntity.ok(ApiResponse.success(message, doctors));
             
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Failed to retrieve doctors: " + e.getMessage()));    
        }
    }
    
    /**
     * Retrieves doctors by their medical specialization.
     * <p>
     * Returns a list of doctors matching the specified specialization.
     * The response includes either:
     * <ul>
     *   <li>A success response with matching doctors (HTTP 200), or</li>
     *   <li>An empty list with a "No doctors found" message if no matches exist, or</li>
     *   <li>An error response if the operation fails (HTTP 500)</li>
     * </ul>
     * </p>
     * 
     * @param specialization the medical specialization to search for (must not be empty)
     * @return ResponseEntity containing:
     *         - ApiResponse with list of doctors and success message (HTTP 200 OK), or
     *         - ApiResponse with empty list and "No doctors found" message (HTTP 200 OK), or
     *         - ApiResponse with error message if specialization is empty (HTTP 400 Bad Request), or
     *         - ApiResponse with error message (HTTP 500 Internal Server Error)
     */
    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<?> getBySpecialization(@PathVariable String specialization) {
        try {
            if(specialization.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, "Specialization is required"));
            }
            List<ViewDoctor> doctors = doctorServiceImp.searchBySpecialization(specialization);
            String message = doctors.isEmpty() 
                ? "No doctors found with this specialization" 
                : "Doctors retrieved successfully";
            return ResponseEntity.ok(ApiResponse.success(message, doctors));
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Failed to retrieve doctors by specialization: " + e.getMessage()));    
        }
    }
    
    /**
     * Searches for doctors by name.
     * <p>
     * Returns a list of doctors whose names contain the search term (case-insensitive).
     * The response includes either:
     * <ul>
     *   <li>A success response with matching doctors (HTTP 200), or</li>
     *   <li>An empty list with a "No doctors found" message if no matches exist, or</li>
     *   <li>An error response if the operation fails (HTTP 500)</li>
     * </ul>
     * </p>
     * 
     * @param name the name or partial name to search for (must not be empty)
     * @return ResponseEntity containing:
     *         - ApiResponse with list of doctors and success message (HTTP 200 OK), or
     *         - ApiResponse with empty list and "No doctors found" message (HTTP 200 OK), or
     *         - ApiResponse with error message if name is empty (HTTP 400 Bad Request), or
     *         - ApiResponse with error message (HTTP 500 Internal Server Error)
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getDoctorByName(@PathVariable String name) {
        try {
            if(name.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, "Doctor's name is required"));
            }
            List<ViewDoctor> doctors = doctorServiceImp.searchByNames(name);
            String message = doctors.isEmpty() 
                ? "No doctors found with this name" 
                : "Doctors retrieved successfully";
            return ResponseEntity.ok(ApiResponse.success(message, doctors));
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Failed to retrieve doctors by name: " + e.getMessage()));    
        }
    }
}