package com.davis.hospital_Appointment_Rest_API.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davis.hospital_Appointment_Rest_API.dto.ViewDoctorSchedule;
import com.davis.hospital_Appointment_Rest_API.model.Doctor;
import com.davis.hospital_Appointment_Rest_API.model.DoctorSchedule;
import com.davis.hospital_Appointment_Rest_API.service.imp.DoctorScheduleServiceImp;
import com.davis.hospital_Appointment_Rest_API.service.imp.DoctorServiceImp;
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
    private final DoctorServiceImp doctorServiceImp;
    
    /**
     * Constructs a new DoctorScheduleController with the specified service implementation.
     * 
     * @param doctorScheduleServiceImp the service implementation for doctor schedule operations
     */
    public DoctorScheduleController(DoctorScheduleServiceImp doctorScheduleServiceImp, DoctorServiceImp doctorServiceImp) {
        this.doctorScheduleServiceImp = doctorScheduleServiceImp;
        this.doctorServiceImp = doctorServiceImp;
    }
    
    /**
     * Creates a new doctor schedule entry for a specific doctor.
     * 
     * @param doctorId the ID of the doctor to associate with this schedule
     * @param doctorSchedule the doctor schedule data to be created (without doctor object)
     * @return ResponseEntity containing the created schedule with HTTP 201 status on success,
     *         error message with HTTP 400 status if creation fails,
     *         or HTTP 500 status for server errors
     */
    @PostMapping("/{doctorId}")
    public ResponseEntity<?> addSchedule(
            @PathVariable String doctorId,
            @RequestBody DoctorSchedule doctorSchedule) {
        try {
            // Find the doctor first
            Optional<Doctor> doctorOptional = doctorServiceImp.findById(doctorId);
            
            if (!doctorOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "Doctor not found with ID: " + doctorId));
            }

            // Associate the doctor with the schedule
            doctorSchedule.setDoctor(doctorOptional.get());
            
            DoctorSchedule savedDoctorSchedule = doctorScheduleServiceImp.save(doctorSchedule);
            
         

            // Convert to DTO - you can't directly cast, you need to construct a new DTO
            ViewDoctorSchedule scheduleDto = new ViewDoctorSchedule(
               savedDoctorSchedule.getDoctor().getSurName()+" "+savedDoctorSchedule.getDoctor().getGivenName()+" "+
            savedDoctorSchedule.getDoctor().getOtherName(),savedDoctorSchedule.getDoctor().getSpecialization(),savedDoctorSchedule.getDayOfWeek(),savedDoctorSchedule.getStartTime(),
            savedDoctorSchedule.getEndTime(),savedDoctorSchedule.getAvailableSlots(),savedDoctorSchedule.isConfirmed()
            
            );
            
            return ResponseEntity.status(HttpStatus.CREATED)
			        .body(new ApiResponse<>(true, "Schedule registered successfully", scheduleDto));
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
    public ResponseEntity<ApiResponse<List<ViewDoctorSchedule>>> getBySpecialization(
            @PathVariable String specialization) {
        try {
            List<ViewDoctorSchedule> doctorSchedules = 
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
    public ResponseEntity<ApiResponse<List<ViewDoctorSchedule>>> getByDoctorName(
            @PathVariable String name) {
        try {
            List<ViewDoctorSchedule> doctorSchedules = doctorScheduleServiceImp.searchByDoctorName(name);
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
    public ResponseEntity<ApiResponse<List<ViewDoctorSchedule>>> getByDayOfWeek(
            @PathVariable String day) {
        try {
            List<ViewDoctorSchedule> doctorSchedules = doctorScheduleServiceImp.findByDayOfWeek(day);
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