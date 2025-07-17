package com.davis.hospital_Appointment_Rest_API.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davis.hospital_Appointment_Rest_API.model.Admin;
import com.davis.hospital_Appointment_Rest_API.model.Doctor;
import com.davis.hospital_Appointment_Rest_API.model.LoginRequest;
import com.davis.hospital_Appointment_Rest_API.model.Patient;
import com.davis.hospital_Appointment_Rest_API.model.Role;
import com.davis.hospital_Appointment_Rest_API.model.User;
import com.davis.hospital_Appointment_Rest_API.service.imp.JwtService;
import com.davis.hospital_Appointment_Rest_API.service.imp.RoleServiceImp;
import com.davis.hospital_Appointment_Rest_API.service.imp.UserServiceImp;
import com.davis.hospital_Appointment_Rest_API.utils.ApiResponse;

/**
 * REST controller for managing user operations in the Hospital Appointment System.
 * Provides endpoints for registering different types of users (Admin, Doctor, Patient)
 * and retrieving user information with appropriate role-based security.
 * 
 * <p>Endpoints follow RESTful conventions and return standardized {@link ApiResponse} formats.</p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-20
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImp userServiceImp;
    private final RoleServiceImp roleServiceImp;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    /**
     * Constructs a new UserController with required dependencies.
     * 
     * @param userServiceImp The service implementation for user operations
     * @param roleServiceImp The service implementation for role operations
     * @param authenticationManager The authentication manager for handling login
     */
    public UserController(UserServiceImp userServiceImp,
                        RoleServiceImp roleServiceImp,
                        AuthenticationManager authenticationManager,
                        JwtService jwtService) {
        this.userServiceImp = userServiceImp;
        this.roleServiceImp = roleServiceImp;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }
    /**
     * Registers a new Admin user.
     * <p>Accessible only to authenticated users with ADMIN role.</p>
     * 
     * @param user The Admin user to be registered
     * @return ResponseEntity containing:
     *         - HTTP 201 (Created) with Admin data if successful
     *         - HTTP 400 (Bad Request) if registration fails
     *         - HTTP 500 (Internal Server Error) for exceptions
     */
   @PreAuthorize("hasRole('ADMIN')")  // Ensures only ADMINs can access this endpoint
    @PostMapping("/admin/register")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin user) {
	   Role role = roleServiceImp.findByName("Admin");
	   if(role != null) {
		   user.setRole(role);
	   }
	   
        return handleUserRegistration(user, "Admin");
    }

    /**
     * Registers a new Doctor user.
     * <p>Accessible only to authenticated users with ADMIN role.</p>
     * 
     * @param user The Doctor user to be registered
     * @return ResponseEntity containing:
     *         - HTTP 201 (Created) with Doctor data if successful
     *         - HTTP 400 (Bad Request) if registration fails
     *         - HTTP 500 (Internal Server Error) for exceptions
     */
    @PreAuthorize("hasRole('ADMIN')")  // Same security restriction as Admin registration
    @PostMapping("/doctor/register")
    public ResponseEntity<?> registerDoctor(@RequestBody Doctor user) {
    		
        return handleUserRegistration(user, "Doctor");
    }

    /**
     * Registers a new Patient user.
     * <p>Publicly accessible endpoint for patient self-registration.</p>
     * 
     * @param user The Patient user to be registered
     * @return ResponseEntity containing:
     *         - HTTP 201 (Created) with Patient data if successful
     *         - HTTP 400 (Bad Request) if registration fails
     *         - HTTP 500 (Internal Server Error) for exceptions
     */
    @PostMapping("/patient/register")  // No security restriction - public access
    public ResponseEntity<?> registerPatient(@RequestBody Patient user) {
        return handleUserRegistration(user, "Patient");
    }

    /**
     * Common method to handle user registration logic.
     * <p>Centralizes the registration workflow for all user types.</p>
     * 
     * @param user The user entity to be registered
     * @param userType Descriptive name of the user type for messages
     * @return ResponseEntity with standardized success/error response
     */
    private ResponseEntity<?> handleUserRegistration(User user, String userType) {
        try {
            // Attempt to save the user through the service layer
            User savedUser = userServiceImp.save(user); 
            
            if (savedUser != null) {
                // Success case - return 201 Created with success message
            		
            	
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ApiResponse<>(true, userType + " registered successfully", savedUser.getUserId()));
            } else {
                // Failure case - return 400 Bad Request
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>(false, userType + " registration failed"));
            }
        } catch (Exception e) {
            // Exception case - return 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Error: " + e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getUsers() {
        // If execution reaches here, user is authenticated and authorized
        List<User> users = userServiceImp.findAll();
        
        String message = users.isEmpty() ? 
            "No users found" : 
            "Users retrieved successfully";
            
        return ResponseEntity.ok(ApiResponse.success(message, users));
    }
    
    /**
     * Authenticates a user and lets the JwtGenerationFilter handle token creation.
     * 
     * <p>This endpoint validates credentials and upon success, the filter will
     * intercept the response to add the JWT token.</p>
     * 
     * @param loginRequest A map containing "username" and "password" fields
     * @return ResponseEntity containing:
     *         - HTTP 200 (OK) with success message if authentication succeeds
     *         - HTTP 401 (Unauthorized) if credentials are invalid
     *         - HTTP 500 (Internal Server Error) for server errors
     */
    @PostMapping("/auth")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginRequest loginRequest) {
        try {
            String username = loginRequest.userName();
            String password = loginRequest.password();

            // Authenticate user credentials
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );
            
            // Set authentication in security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = jwtService.generateJwtToken(authentication);
            
            
            // The JwtGenerationFilter will handle token creation
            return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + jwtToken)  // Set in Authorization header

                .body(ApiResponse.success("Authentication successful",jwtToken));
                
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.error("Invalid username or password"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Authentication failed: " + e.getMessage()));
        }
    }
}



