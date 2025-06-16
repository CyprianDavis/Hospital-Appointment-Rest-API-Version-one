package com.davis.hospital_Appointment_Rest_API.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.davis.hospital_Appointment_Rest_API.model.DoctorSchedule;
import com.davis.hospital_Appointment_Rest_API.service.imp.DoctorScheduleServiceImp;

@RequestMapping("/doctorSchedule")
public class DoctorScheduleController {
	@Autowired
	private DoctorScheduleServiceImp doctorScheduleServiceImp;
	@PostMapping("/schedule")
	public ResponseEntity<String> addSchedule(@RequestBody DoctorSchedule doctorSchedule){
		try {
			if(doctorSchedule.equals(doctorScheduleServiceImp.save(doctorSchedule))) {
				return ResponseEntity.status(HttpStatus.CREATED)
						.body("Operation Successful");
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Operation Failed");
			}
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An exception occurred: "+e.getMessage());
		}
	}
	@GetMapping("/schedule")
	public List<DoctorSchedule> getDoctorSchedules(){
		return doctorScheduleServiceImp.findAll();
	}
	@GetMapping("/specialization")
	public List<DoctorSchedule> getDoctorSchedulesBySpecialization(@PathVariable("specialization")String specialization){
		return doctorScheduleServiceImp.findByDoctorSpecialization(specialization);	
	}
	@GetMapping("/name")
	public List<DoctorSchedule> getByDoctorName(@PathVariable("name")String name){
		return doctorScheduleServiceImp.searchByDoctorName(name);
	}
	@GetMapping("/dayOfWeek")
	public List<DoctorSchedule> getByDayOfWeek(@PathVariable("day")String day){
		return doctorScheduleServiceImp.findByDayOfWeek(day);
		
	}
	

}
