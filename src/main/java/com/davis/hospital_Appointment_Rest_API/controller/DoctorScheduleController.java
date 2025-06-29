package com.davis.hospital_Appointment_Rest_API.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davis.hospital_Appointment_Rest_API.model.DoctorSchedule;
import com.davis.hospital_Appointment_Rest_API.service.imp.DoctorScheduleServiceImp;
import com.davis.hospital_Appointment_Rest_API.utils.ApiResponse;

/**
 * REST controller for managing doctor schedules.
 * Provides endpoints for creating, retrieving, and searching doctor schedules
 * by various criteria such as specialization, name, and day of week.
 * @author CYPRIAN DAVIS
 */
@RestController
@RequestMapping("/api/doctor-schedules")
public class DoctorScheduleController {
    
    private final DoctorScheduleServiceImp doctorScheduleServiceImp;
    
    /**
     * Constructs a new DoctorScheduleController with the specified service implementation.
     * 
     * @param doctorScheduleServiceImp the service implementation for doctor schedule operations
     */
    public DoctorScheduleController(DoctorScheduleServiceImp doctorScheduleServiceImp) {
        this.doctorScheduleServiceImp = doctorScheduleServiceImp;
    }
    
    /**
     * Creates a new doctor schedule entry.
     * 
     * @param doctorSchedule the doctor schedule data to be created
     * @return ResponseEntity containing the created schedule with HTTP 201 status on success,
     *         error message with HTTP 400 status if creation fails,
     *         or HTTP 500 status for server errors
     */
    @PostMapping
    public ResponseEntity<?> addSchedule(@RequestBody DoctorSchedule doctorSchedule) {
        try {
            DoctorSchedule savedDoctorSchedule = doctorScheduleServiceImp.save(doctorSchedule);
            
            if(savedDoctorSchedule != null) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ApiResponse<>(true, "Schedule registered successfully", savedDoctorSchedule));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>(false, "Schedule registration failed"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Error: " + e.getMessage()));
        }
    }
    
    /**
     * Retrieves all doctor schedules from the system.
     * 
     * @return ResponseEntity containing a list of all doctor schedules with HTTP 200 status,
     *         or an error message with HTTP 500 status if retrieval fails
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<DoctorSchedule>>> getAllDoctorSchedules() {
        try {
            List<DoctorSchedule> doctorSchedules = doctorScheduleServiceImp.findAll();
            String message = doctorSchedules.isEmpty() ?
                    "No schedules found" :
                    "Schedules retrieved successfully";
            return ResponseEntity.ok(ApiResponse.success(message, doctorSchedules));
           
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to retrieve schedules: " + e.getMessage()));    
        }
    }
    
    /**
     * Retrieves doctor schedules by medical specialization.
     * 
     * @param specialization the medical specialization to filter by (e.g., "Cardiology")
     * @return ResponseEntity containing matching schedules with HTTP 200 status,
     *         or an error message with HTTP 500 status if retrieval fails
     */
    @GetMapping("/by-specialization/{specialization}")
    public ResponseEntity<ApiResponse<List<DoctorSchedule>>> getBySpecialization(
            @PathVariable String specialization) {
        try {
            List<DoctorSchedule> doctorSchedules = 
                doctorScheduleServiceImp.findByDoctorSpecialization(specialization);
            String message = doctorSchedules.isEmpty() ?
                    "No schedules found for specialization: " + specialization :
                    "Schedules retrieved successfully";
            return ResponseEntity.ok(ApiResponse.success(message, doctorSchedules));
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to retrieve schedules by specialization: " + e.getMessage()));    
        }
    }
    
    /**
     * Retrieves doctor schedules by doctor name.
     * 
     * @param name the doctor's name to filter by
     * @return ResponseEntity containing matching schedules with HTTP 200 status,
     *         or an error message with HTTP 500 status if retrieval fails
     */
    @GetMapping("/by-name/{name}")
    public ResponseEntity<ApiResponse<List<DoctorSchedule>>> getByDoctorName(
            @PathVariable String name) {
        try {
            List<DoctorSchedule> doctorSchedules = doctorScheduleServiceImp.findByDoctorName(name);
            String message = doctorSchedules.isEmpty() ?
                    "No schedules found for doctor: " + name :
                    "Schedules retrieved successfully";
            return ResponseEntity.ok(ApiResponse.success(message, doctorSchedules));
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to retrieve schedules by doctor name: " + e.getMessage()));  
        }
    }
    
    /**
     * Retrieves doctor schedules by day of week.
     * 
     * @param day the day of week to filter by (e.g., "Monday")
     * @return ResponseEntity containing matching schedules with HTTP 200 status,
     *         or an error message with HTTP 500 status if retrieval fails
     */
    @GetMapping("/by-day/{day}")
    public ResponseEntity<ApiResponse<List<DoctorSchedule>>> getByDayOfWeek(
            @PathVariable String day) {
        try {
            List<DoctorSchedule> doctorSchedules = doctorScheduleServiceImp.findByDayOfWeek(day);
            String message = doctorSchedules.isEmpty() ?
                    "No schedules found for day: " + day :
                    "Schedules retrieved successfully";
            return ResponseEntity.ok(ApiResponse.success(message, doctorSchedules));
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to retrieve schedules by day: " + e.getMessage()));  
        }
    }
}