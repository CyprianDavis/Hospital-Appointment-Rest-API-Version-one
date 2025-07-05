package com.davis.hospital_Appointment_Rest_API.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.davis.hospital_Appointment_Rest_API.utils.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Custom implementation of {@link AuthenticationEntryPoint} that handles authentication failures
 * by returning a standardized JSON error response using the {@link ApiResponse} format.
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    
    private final ObjectMapper objectMapper;
    
    public CustomAuthenticationEntryPoint() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        
        String errorMessage;
        String errorCode = "AUTH_ERROR";
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        
        if (authException instanceof BadCredentialsException) {
            errorMessage = "Invalid username or password";
            errorCode = "BAD_CREDENTIALS";
        } else if (authException instanceof InsufficientAuthenticationException) {
            errorMessage = "Invalid username or password";
            errorCode = "UNAUTHENTICATED";
        } else {
            errorMessage = "Invalid username or password";
            errorCode = "AUTH_FAILURE";
        }
        
        // Create detailed error response
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("errorCode", errorCode);
        errorDetails.put("message", errorMessage);
        errorDetails.put("path", request.getRequestURI());
        
        ApiResponse<Map<String, String>> apiResponse = ApiResponse.error(
            "Authentication failed", 
            errorDetails
        );
        
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(status.value());
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }
}