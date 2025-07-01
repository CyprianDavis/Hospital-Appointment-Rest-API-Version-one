package com.davis.hospital_Appointment_Rest_API.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when a requested resource is not found in the system.
 * <p>
 * This exception extends {@link ApiException} and automatically sets:
 * <ul>
 *   <li>HTTP status code to {@link HttpStatus#NOT_FOUND} (404)</li>
 *   <li>Error code to "RESOURCE_NOT_FOUND"</li>
 * </ul>
 * The exception message is constructed from the resource details for clarity.
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @see ApiException
 * @see HttpStatus
 * @since 2025-07-01
 */
public class ResourceNotFoundException extends ApiException {
    
    /**
     * Serial version UID for serialization compatibility.
     * <p>
     * This field maintains version control for serialized instances of this exception.
     * </p>
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new ResourceNotFoundException with details about the missing resource.
     *
     * @param resourceName the name/type of the resource that wasn't found (e.g., "Patient", "Appointment")
     * @param fieldName the name of the field that was searched (e.g., "id", "email")
     * @param fieldValue the value that was searched for (e.g., "123", "patient@example.com")
     * 
     */
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        // Constructs a formatted message and passes to parent ApiException
        super(
            String.format("%s not found with %s: %s", resourceName, fieldName, fieldValue), 
            HttpStatus.NOT_FOUND,
            "RESOURCE_NOT_FOUND"
        );
    }
}