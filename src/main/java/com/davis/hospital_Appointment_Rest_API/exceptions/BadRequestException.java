package com.davis.hospital_Appointment_Rest_API.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when the client sends a malformed or invalid request.
 * <p>
 * This exception should be used to indicate that:
 * <ul>
 *   <li>The request syntax is invalid</li>
 *   <li>The request contains invalid parameters</li>
 *   <li>The request violates business rules</li>
 *   <li>Required parameters are missing</li>
 * </ul>
 * Automatically sets:
 * <ul>
 *   <li>HTTP status code to {@link HttpStatus#BAD_REQUEST} (400)</li>
 *   <li>Error code to "BAD_REQUEST"</li>
 * </ul>
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @see ApiException
 * @see HttpStatus
 * @since 2025-07-01
 */
public class BadRequestException extends ApiException {
    
    /**
     * Serial version UID for serialization compatibility.
     * <p>
     * This field maintains version control for serialized instances of this exception.
     * </p>
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new BadRequestException with the specified detail message.
     *
     * @param message the detailed error message that explains what was wrong with the request.
     *                Should be clear enough to help API consumers fix their request.
     *                Example: "Email address must be properly formatted"
     * 
     */
    public BadRequestException(String message) {
        
        // Constructs the exception with BAD_REQUEST status and standard error code
        super(message, HttpStatus.BAD_REQUEST, "BAD_REQUEST");
    }
}