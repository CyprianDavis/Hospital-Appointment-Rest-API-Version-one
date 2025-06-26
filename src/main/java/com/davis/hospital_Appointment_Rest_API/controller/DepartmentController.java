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

import com.davis.hospital_Appointment_Rest_API.model.Department;
import com.davis.hospital_Appointment_Rest_API.service.imp.DepartmentServiceImp;
import com.davis.hospital_Appointment_Rest_API.utils.ApiResponse;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
	private final DepartmentServiceImp departmentServiceImp;
	
	public DepartmentController (DepartmentServiceImp departmentServiceImp) {
		this.departmentServiceImp = departmentServiceImp;
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<Department>>> getDepartments(){
		try {
			List<Department> departments = departmentServiceImp.findAll();
			
			String message = departments.isEmpty() ?
					"No Departments found" :
						"Departments retrieved Successfully";
			
            return ResponseEntity.ok(ApiResponse.success(message, departments));

		}catch (Exception e) {
			// TODO: handle exception
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body(ApiResponse.error("Failed to retrieve Departments: "+e.getMessage()));
		}
	}
	@PostMapping
	public ResponseEntity<?> addDepartment(@RequestBody Department depart){
		try {
			Department savedDepartment = departmentServiceImp.save(depart);
			if(savedDepartment != null) {
				   return ResponseEntity.status(HttpStatus.CREATED)
	                        .body(new ApiResponse<>(true, "Department registered successfully", savedDepartment));
			}else {
				 return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                        .body(new ApiResponse<>(false, "Department registration failed"));
			}
			
		}catch (Exception e) {
			// TODO: handle 
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body(new ApiResponse<>(false, "Error: " + e.getMessage()));
		}
	}
	@GetMapping("/deparment/{name}")
	public ResponseEntity<ApiResponse<Optional<Department>>> getDepartmentByName(@PathVariable("name")String depart){
		try {
			Optional<Department> department = departmentServiceImp.findByName(depart);
			if(department != null) {
	            return ResponseEntity.ok(ApiResponse.success("Department found", department));
			}else {
				  return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                        .body(ApiResponse.error("Department with name '" + depart + "' not found"));
			}
		}catch (Exception e) {
			// TODO: handle exception
			 // Exception case - return 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error searching for Departments: " + e.getMessage()));
		}
		
	}
	@GetMapping("/department/{locationCode}")
	public ResponseEntity<ApiResponse<Optional<Department>>> getDepartmentByLocationCode(@PathVariable("code")String code){
		try {
			Optional<Department> department = departmentServiceImp.findByLocationCode(code);
			if(department != null ) {
	            return ResponseEntity.ok(ApiResponse.success("Department found", department));				
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Department with Location code '" + code + "' not found"));
			}

			
		}catch (Exception e) {
			// TODO: handle exception
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body(ApiResponse.error("Error searching for Department: " + e.getMessage()));
		}
		
	}
}
