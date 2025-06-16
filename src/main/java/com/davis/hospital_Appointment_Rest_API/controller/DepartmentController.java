package com.davis.hospital_Appointment_Rest_API.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.davis.hospital_Appointment_Rest_API.model.Department;
import com.davis.hospital_Appointment_Rest_API.service.imp.DepartmentServiceImp;

@RestController
public class DepartmentController {
	@Autowired
	private DepartmentServiceImp departmentServiceImp;
	
	@GetMapping
	public List<Department> getDepartments(){
		return departmentServiceImp.findAll();
	}
	@PostMapping("/department")
	public ResponseEntity<String> addDepartment(@RequestBody Department depart){
		try {
			if(depart.equals(departmentServiceImp.save(depart))) {
				return ResponseEntity.status(HttpStatus.CREATED).
						body("Operation Successful");
			}
			else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                        body("User registration failed");
			}
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("An exception occurred: " + e.getMessage());
		}
		
	}
	@GetMapping("/departName")
	public Optional<Department> getDepartmentByName(@PathVariable("name")String depart){
		return departmentServiceImp.findByName(depart);
	}
	@GetMapping("/locationCode")
	public Optional<Department> getDepartmentByLocationCode(@PathVariable("code")String code){
		return departmentServiceImp.findByLocationCode(code);
		
	}
}
