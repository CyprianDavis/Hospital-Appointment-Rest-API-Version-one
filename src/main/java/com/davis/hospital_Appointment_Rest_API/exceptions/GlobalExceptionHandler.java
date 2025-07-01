package com.davis.hospital_Appointment_Rest_API.exceptions;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.davis.hospital_Appointment_Rest_API.utils.ApiResponse;

/**
 * Global exception handler for the Hospital Appointment REST API.
 * <p>
 * This class handles exceptions thrown by controllers and converts them
 * into standardized API error responses using {@link ApiResponse}.
 * </p>
 * 
 * <p>
 * Handles the following exception types:
 * <ul>
 *   <li>{@link ApiException} - Custom API exceptions</li>
 *   <li>{@link MethodArgumentNotValidException} - Validation failures</li>
 *   <li>{@link Exception} - All other unexpected exceptions</li>
 * </ul>
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @see ControllerAdvice
 * @see ExceptionHandler
 * @since 2025-07-01
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles custom API exceptions.
     * <p>
     * Processes exceptions of type {@link ApiException} and converts them
     * into standardized error responses.
     * </p>
     *
     * @param ex The caught API exception
     * @return ResponseEntity containing error details in {@link ApiResponse} format
     * 
     * @implNote
     * Creates a response with:
     * <ul>
     *   <li>HTTP status from the exception</li>
     *   <li>Error details including error code and message</li>
     *   <li>Standardized response format</li>
     * </ul>
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<Object>> handleApiException(ApiException ex) {
        // Create map for error details
        Map<String, String> errorDetails = new HashMap<>();
        // Add error code from exception
        errorDetails.put("errorCode", ex.getErrorCode());
        // Add exception message
        errorDetails.put("message", ex.getMessage());
        
        // Create error response using ApiResponse utility
        ApiResponse<Object> response = ApiResponse.error(
            ex.getStatus().getReasonPhrase(),
            errorDetails
        );
        
        // Return response with appropriate HTTP status
        return new ResponseEntity<>(response, ex.getStatus());
    }

    /**
     * Handles validation exceptions.
     * <p>
     * Processes Spring's {@link MethodArgumentNotValidException} that occurs
     * when request body validation fails.
     * </p>
     *
     * @param ex The caught validation exception
     * @return ResponseEntity containing validation errors in {@link ApiResponse} format
     * 
     * @implNote
     * Extracts field errors and converts them to a map where:
     * <ul>
     *   <li>Key is the field name</li>
     *   <li>Value is the validation error message</li>
     * </ul>
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        
        // Convert field errors to map of field names to error messages
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        // Key mapper - field name
                        FieldError::getField,
                        // Value mapper - error message or default
                        fieldError -> fieldError.getDefaultMessage() == null ? 
                                "Validation error" : fieldError.getDefaultMessage()
                ));
        
        // Create error response with validation errors
        ApiResponse<Map<String, String>> response = 
                ApiResponse.error("Validation failed", errors);
        
        // Return bad request response with validation errors
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles all other unexpected exceptions.
     * <p>
     * Acts as a fallback handler for any exception not specifically handled.
     * </p>
     *
     * @param ex The caught exception
     * @return ResponseEntity with generic error message
     * 
     * @implNote
     * Returns:
     * <ul>
     *   <li>HTTP 500 status code</li>
     *   <li>Generic error message</li>
     *   <li>No technical details exposed</li>
     * </ul>
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception ex) {
        // Create generic error response
        ApiResponse<Void> response = ApiResponse.error("An unexpected error occurred");
        // Return internal server error response
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}