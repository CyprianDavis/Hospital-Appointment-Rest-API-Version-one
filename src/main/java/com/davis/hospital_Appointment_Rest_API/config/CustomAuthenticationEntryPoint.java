package com.davis.hospital_Appointment_Rest_API.config;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.davis.hospital_Appointment_Rest_API.utils.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Custom implementation of {@link AuthenticationEntryPoint} that handles authentication failures
 * by returning a standardized JSON error response.
 * 
 * <p>This component intercepts all unauthorized requests and returns a consistent error format
 * using the {@link ApiResponse} wrapper class. It replaces the default Spring Security behavior
 * which typically redirects to a login page or returns a basic HTTP 401 response.</p>
 * 
 * <p><b>Key Responsibilities:</b></p>
 * <ul>
 *   <li>Sets appropriate HTTP status code (401 Unauthorized)</li>
 *   <li>Configures response content type as application/json</li>
 *   <li>Constructs a standardized error response body</li>
 *   <li>Handles various types of authentication exceptions</li>
 * </ul>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-20
 * @see AuthenticationEntryPoint
 * @see ApiResponse
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    
    /**
     * Handles authentication failures by sending a JSON error response.
     * 
     * <p>This method is invoked when an unauthenticated user attempts to access a secured resource
     * or when authentication credentials are invalid. It overrides the default Spring Security
     * behavior to provide a more REST-friendly response.</p>
     * 
     * @param request The HTTP request that resulted in an authentication exception
     * @param response The HTTP response where the error will be written
     * @param authException The authentication exception that triggered this entry point
     * @throws IOException if an input or output exception occurs while writing to the response
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        
        // Set response content type to JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        
        // Set HTTP status code to 401 Unauthorized
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        
        // Create standardized error response
        ApiResponse<?> apiResponse = ApiResponse.error(
            "Authentication required: " + authException.getMessage()
        );
        
        // Convert the ApiResponse object to JSON and write it to the response
        response.getWriter().write(new ObjectMapper().writeValueAsString(apiResponse));
    }
}