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

import com.davis.hospital_Appointment_Rest_API.model.Authority;
import com.davis.hospital_Appointment_Rest_API.service.imp.AuthorityServiceImp;
import com.davis.hospital_Appointment_Rest_API.utils.ApiResponse;

/**
 * REST controller for managing authority-related operations.
 * Provides endpoints for retrieving, adding, and finding authorities by name.
 * 
 * @author CYPRIAN DAVIS
 */
@RestController
@RequestMapping("/api/userAuthorities")
public class AuthorityController {
    
    private final AuthorityServiceImp authorityServieImp;
    
    /**
     * Constructs an AuthorityController with the required service implementation.
     * 
     * @param authorityServieImp The service implementation for authority operations
     */
    public AuthorityController(AuthorityServiceImp authorityServieImp) {
        this.authorityServieImp = authorityServieImp;
    }
    
    /**
     * Retrieves all authorities from the system.
     * 
     * @return ResponseEntity containing a list of authorities and operation status
     *         - Returns 200 OK with authorities if successful
     *         - Returns 500 Internal Server Error if an exception occurs
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<Authority>>> getAuthorities(){
        try {
            // Retrieve all authorities from the service layer
            List<Authority> authorities = authorityServieImp.findAll();
            
            // Determine appropriate message based on whether authorities were found
            String message = authorities.isEmpty() ?
                    "No Authorities found" :
                        "Authorities retrieved Successfully";
                        
            // Return successful response with authorities list
            return ResponseEntity.ok(ApiResponse.success(message, authorities));
        } catch(Exception e) {
            // Return error response if exception occurs during processing
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to retrieve User Roles: "+e.getMessage()));
        }
    }
    
    /**
     * Adds a new authority to the system.
     * 
     * @param authority The authority object to be added (received as JSON in request body)
     * @return ResponseEntity indicating the result of the operation:
     *         - Returns 201 Created with the saved authority if successful
     *         - Returns 400 Bad Request if the operation fails
     *         - Returns 500 Internal Server Error if an exception occurs
     */
    @PostMapping
    public ResponseEntity<?> addAuthority(@RequestBody Authority authority){
        try {
            // Attempt to save the new authority
            Authority savedAuthority = authorityServieImp.save(authority);
            
            if(savedAuthority != null) {
                // Success case - return 201 Created with success message
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ApiResponse<>(true, "Authority registered successfully", savedAuthority));
            } else {
                // Failure case - return 400 Bad Request
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>(false, "Authority registration failed"));
            }
        } catch(Exception e) {
            // Exception case - return 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Error: " + e.getMessage()));
        }
    }
    
    /**
     * Retrieves a specific authority by its name.
     * 
     * @param name The name of the authority to retrieve (path variable)
     * @return ResponseEntity containing the found authority and operation status:
     *         - Returns 200 OK with the authority if found
     *         - Returns 404 Not Found if the authority doesn't exist
     *         - Returns 500 Internal Server Error if an exception occurs
     */
    @GetMapping("/authority/{name}")
    public ResponseEntity<ApiResponse<Authority>> getByName(@PathVariable("name") String name) {
        try {
            // Attempt to find authority by name
            Authority authority = authorityServieImp.findByName(name);
            
            if(authority != null) {
                // Authority found - return 200 OK with the authority
                return ResponseEntity.ok(ApiResponse.success("Authority found", authority));
            } else {
                // Authority not found - return 404 Not Found
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Authority with name '" + name + "' not found"));
            }
        } catch(Exception e) {
            // Exception case - return 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error searching for authority: " + e.getMessage()));
        }
    }
}