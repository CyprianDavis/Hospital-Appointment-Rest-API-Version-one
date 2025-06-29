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
@RestController
@RequestMapping("/api/doctorSchedule")
public class DoctorScheduleController {
	
	private final DoctorScheduleServiceImp doctorScheduleServiceImp;
	
	public DoctorScheduleController(DoctorScheduleServiceImp doctorScheduleServiceImp) {
		this.doctorScheduleServiceImp = doctorScheduleServiceImp;
	}
	@PostMapping
	public ResponseEntity<?> addSchedule(@RequestBody DoctorSchedule doctorSchedule){
		try {
			DoctorSchedule savedDoctorSchedule = doctorScheduleServiceImp.save(doctorSchedule);
			
			if(savedDoctorSchedule !=null) {
				 return ResponseEntity.status(HttpStatus.CREATED)
	                        .body(new ApiResponse<>(true, "Schedule registered successfully", savedDoctorSchedule));
			}else {
				 return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                        .body(new ApiResponse<>(false, "Schedule registration failed"));
			}
		}catch (Exception e) {
			// TODO: handle exception
			// Exception case - return 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Error: " + e.getMessage()));
		}
	}
	@GetMapping
	public ResponseEntity<ApiResponse<List<DoctorSchedule>>>  getDoctorSchedules(){
		try {
			List<DoctorSchedule> doctorSchedules = doctorScheduleServiceImp.findAll();
			 String message = doctorSchedules.isEmpty() ?
	                    "No Schedules found" :
	                        "Schedules retrieved successfully";
	            return ResponseEntity.ok(ApiResponse.success(message,doctorSchedules));
	           
		}catch (Exception e) {
			// TODO: handle exception
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body(ApiResponse.error("Failed to retrieve User Roles: "+e.getMessage()));    
			
		}
		
	}
		
	@GetMapping("/{specialization}")
	public ResponseEntity<ApiResponse<List<DoctorSchedule>>> getDoctorSchedulesBySpecialization(@PathVariable String specialization){
		try {
			List<DoctorSchedule> doctorSchedules = doctorScheduleServiceImp.findByDoctorSpecialization(specialization);
			String message = doctorSchedules.isEmpty() ?
                    "No Schedules found" :
                        "Schedules retrieved successfully";
            return ResponseEntity.ok(ApiResponse.success(message,doctorSchedules));
			
			// TODO: handle exception
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to retrieve User Roles: "+e.getMessage()));    
		}
		
	}
	@GetMapping("/{name}")
	public ResponseEntity<ApiResponse<List<DoctorSchedule>>> getByDoctorName(@PathVariable String name){
		try {
			List<DoctorSchedule> doctorSchedules = doctorScheduleServiceImp.findByDoctorSpecialization(name);
			String message = doctorSchedules.isEmpty() ?
                    "No Schedules found" :
                        "Schedules retrieved successfully";
            return ResponseEntity.ok(ApiResponse.success(message,doctorSchedules));
			
		}catch (Exception e) {
			// TODO: handle exception
						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			                    .body(ApiResponse.error("Failed to retrieve User Roles: "+e.getMessage()));  
		}
	}
	@GetMapping("/{dayOfWeek}")
	public ResponseEntity<ApiResponse<List<DoctorSchedule>>> getByDayOfWeek(@PathVariable String day){
		try {
			List<DoctorSchedule> doctorSchedules = doctorScheduleServiceImp.findByDayOfWeek(day);
			String message = doctorSchedules.isEmpty() ?
                    "No Schedules found" :
                        "Schedules retrieved successfully";
            return ResponseEntity.ok(ApiResponse.success(message,doctorSchedules));
			
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to retrieve User Roles: "+e.getMessage()));  
		}
		
		
	}
	

}
