package com.davis.hospital_Appointment_Rest_API.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.davis.hospital_Appointment_Rest_API.model.Department;
import com.davis.hospital_Appointment_Rest_API.service.imp.DepartmentServiceImp;
import com.davis.hospital_Appointment_Rest_API.utils.ApiResponse;

/**
 * REST controller for managing hospital departments.
 * Provides endpoints for retrieving, adding, and finding departments by various criteria.
 * 
 * @author CYPRIAN DAVIS
 */
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    
    private final DepartmentServiceImp departmentServiceImp;
    
    /**
     * Constructs a DepartmentController with the required service implementation.
     * 
     * @param departmentServiceImp The service implementation for department operations
     */
    public DepartmentController(DepartmentServiceImp departmentServiceImp) {
        this.departmentServiceImp = departmentServiceImp;
    }
    
    /**
     * Retrieves all departments from the system.
     * 
     * @return ResponseEntity containing a list of departments and operation status:
     *         - 200 OK with departments if successful
     *         - 500 Internal Server Error if an exception occurs
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<Department>>> getDepartments() {
        try {
            // Retrieve all departments from the service layer
            List<Department> departments = departmentServiceImp.findAll();
            
            // Determine appropriate message based on whether departments were found
            String message = departments.isEmpty() ?
                    "No Departments found" :
                    "Departments retrieved Successfully";
            
            return ResponseEntity.ok(ApiResponse.success(message, departments));

        } catch (Exception e) {
            // Return error response if exception occurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to retrieve Departments: "+e.getMessage()));
        }
    }
    
    /**
     * Adds a new department to the system.
     * 
     * @param depart The department object to be added (received as JSON in request body)
     * @return ResponseEntity indicating the result of the operation:
     *         - 201 Created with the saved department if successful
     *         - 400 Bad Request if the operation fails
     *         - 500 Internal Server Error if an exception occurs
     */
    @PostMapping
    public ResponseEntity<?> addDepartment(@RequestBody Department depart) {
        try {
            // Attempt to save the new department
            Department savedDepartment = departmentServiceImp.save(depart);
            
            if (savedDepartment != null) {
                // Success case - return 201 Created with success message
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ApiResponse<>(true, "Department registered successfully", savedDepartment));
            } else {
                // Failure case - return 400 Bad Request
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>(false, "Department registration failed"));
            }
            
        } catch (Exception e) {
            // Exception case - return 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Error: " + e.getMessage()));
        }
    }
    
    /**
     * Retrieves a department by its name.
     * 
     * @param depart The name of the department to retrieve (path variable)
     * @return ResponseEntity containing the found department and operation status:
     *         - 200 OK with the department if found
     *         - 404 Not Found if the department doesn't exist
     *         - 500 Internal Server Error if an exception occurs
     */
    @GetMapping("/department/{name}")
    public ResponseEntity<ApiResponse<Optional<Department>>> getDepartmentByName(
            @PathVariable("name") String depart) {
        try {
            // Attempt to find department by name
            Optional<Department> department = departmentServiceImp.findByName(depart);
            
            if (department != null && department.isPresent()) {
                return ResponseEntity.ok(ApiResponse.success("Department found", department));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Department with name '" + depart + "' not found"));
            }
        } catch (Exception e) {
            // Exception case - return 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error searching for Departments: " + e.getMessage()));
        }
    }
    
    /**
     * Retrieves a department by its location code.
     * 
     * @param code The location code of the department to retrieve (path variable)
     * @return ResponseEntity containing the found department and operation status:
     *         - 200 OK with the department if found
     *         - 404 Not Found if the department doesn't exist
     *         - 500 Internal Server Error if an exception occurs
     */
    @GetMapping("/department/{locationCode}")
    public ResponseEntity<ApiResponse<Optional<Department>>> getDepartmentByLocationCode(
            @PathVariable("locationCode") String code) {
        try {
            // Attempt to find department by location code
            Optional<Department> department = departmentServiceImp.findByLocationCode(code);
            
            if (department != null && department.isPresent()) {
                return ResponseEntity.ok(ApiResponse.success("Department found", department));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Department with Location code '" + code + "' not found"));
            }
            
        } catch (Exception e) {
            // Exception case - return 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error searching for Department: " + e.getMessage()));
        }
    }
}