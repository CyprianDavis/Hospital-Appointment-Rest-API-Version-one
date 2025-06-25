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

import com.davis.hospital_Appointment_Rest_API.model.Role;
import com.davis.hospital_Appointment_Rest_API.service.imp.RoleServiceImp;
import com.davis.hospital_Appointment_Rest_API.utils.ApiResponse;

/**
 * Controller for handling role-related operations.
 * Provides endpoints for retrieving, adding, and finding roles by name.
 * @author CYPRIAN DAVIS
 */
@RestController
@RequestMapping("/api/userRoles")
public class RolesController {

    private final RoleServiceImp roleServiceImp;
    
    /**
     * Constructor for dependency injection of RoleServiceImp
     * @param roleServiceImp The role service implementation
     */
    public RolesController(RoleServiceImp roleServiceImp) {
        this.roleServiceImp = roleServiceImp;
    }
    
    /**
     * Retrieves all roles from the system.
     * @return ResponseEntity containing a list of roles and operation status
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<Role>>> getRoles(){
        try {
            // Retrieve all roles from the service
            List<Role> roles = roleServiceImp.findAll();
            
            // Determine appropriate message based on whether roles were found
            String message = roles.isEmpty() ?
                    "No Roles found" :
                        "Roles retrieved successfully";
                        
            // Return successful response with roles
            return ResponseEntity.ok(ApiResponse.success(message,roles));
            
        }catch(Exception e) {
            // Return error response if exception occurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to retrieve User Roles: "+e.getMessage()));    
        }
    }
    
    /**
     * Adds a new role to the system.
     * @param role The role to be added
     * @return ResponseEntity indicating success or failure of the operation
     */
    @PostMapping("/role")
    public ResponseEntity<?> addRole(@RequestBody Role role){
        try {
            // Attempt to save the new role
            Role savedRole = roleServiceImp.save(role);
            
            if(savedRole != null) {
                // Success case - return 201 Created with success message
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ApiResponse<>(true, "Role registered successfully", savedRole));
            } else {
                // Failure case - return 400 Bad Request
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>(false, "Role registration failed"));
            }
        } catch(Exception e) {
            // Exception case - return 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Error: " + e.getMessage()));
        }
    }
                        
    /**
     * Retrieves a role by its name.
     * @param name The name of the role to retrieve
     * @return ResponseEntity containing the found role or error message
     */
    @GetMapping("/role/{name}")
    public ResponseEntity<ApiResponse<Role>> getByName(@PathVariable("name") String name) {
        try {
            // Attempt to find the role by name
            Role role = roleServiceImp.findByName(name);
            
            if(role != null) {
                // Role found - return 200 OK with the role
                return ResponseEntity.ok(ApiResponse.success("Role found", role));
            } else {
                // Role not found - return 404 Not Found
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Role with name '" + name + "' not found"));
            }
        } catch(Exception e) {
            // Exception case - return 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error searching for role: " + e.getMessage()));
        }
    }
}