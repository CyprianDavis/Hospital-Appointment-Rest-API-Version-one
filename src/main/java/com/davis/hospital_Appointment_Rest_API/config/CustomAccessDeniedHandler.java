package com.davis.hospital_Appointment_Rest_API.config;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.davis.hospital_Appointment_Rest_API.utils.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Custom implementation of {@link AccessDeniedHandler} that handles authorization failures
 * by returning a standardized JSON error response.
 * 
 * <p>This component intercepts requests from authenticated users who lack the necessary
 * permissions to access a resource, providing a consistent error format using the
 * {@link ApiResponse} wrapper class.</p>
 * 
 * <p><b>Key Responsibilities:</b></p>
 * <ul>
 *   <li>Sets appropriate HTTP status code (403 Forbidden)</li>
 *   <li>Configures response content type as application/json</li>
 *   <li>Constructs a standardized error response body</li>
 *   <li>Differentiates between authentication and authorization failures</li>
 * </ul>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-20
 * @see AccessDeniedHandler
 * @see ApiResponse
 * @see CustomAuthenticationEntryPoint
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * Handles authorization failures by sending a JSON error response.
     * 
     * <p>This method is invoked when an authenticated user attempts to access a resource
     * for which they don't have sufficient permissions. It provides a REST-friendly
     * response format instead of the default Spring Security behavior.</p>
     * 
     * @param request The HTTP request that resulted in an access denied exception
     * @param response The HTTP response where the error will be written
     * @param accessDeniedException The authorization exception that triggered this handler
     * @throws IOException if an input or output exception occurs while writing to the response
     * @throws ServletException if a servlet-specific error occurs
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        
        // Set response content type to JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        
        // Set HTTP status code to 403 Forbidden (authorization failure)
        response.setStatus(HttpStatus.FORBIDDEN.value());
        
        // Create standardized error response with descriptive message
        ApiResponse<?> apiResponse = ApiResponse.error(
            "Access denied: " + accessDeniedException.getMessage()
        );
        
        // Convert the ApiResponse object to JSON and write it to the response
        response.getWriter().write(new ObjectMapper().writeValueAsString(apiResponse));
    }
}