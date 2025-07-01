package com.davis.hospital_Appointment_Rest_API.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Base exception class for API-specific exceptions in the Hospital Appointment REST API.
 * <p>
 * This class extends {@link RuntimeException} and provides additional fields to track
 * HTTP status codes and optional error codes for better error handling and reporting.
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @see RuntimeException
 * @see HttpStatus
 * @since 2025-07-01
 */
public class ApiException extends RuntimeException {
    
    /**
     * Serial version UID for serialization compatibility.
     * <p>
     * This field maintains version control for serialized instances of this exception.
     * </p>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * The HTTP status code associated with this exception.
     * <p>
     * This field is final to ensure immutability of the exception once created.
     * </p>
     */
    private final HttpStatus status;
    
    /**
     * An optional error code for more detailed error categorization.
     * <p>
     * This can be used to provide machine-readable error codes in API responses.
     * </p>
     */
    private final String errorCode;
    
    /**
     * Constructs a new API exception with the specified detail message and HTTP status.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method)
     * @param status  the HTTP status code (which is saved for later retrieval
     *                by the {@link #getStatus()} method)
     */
    public ApiException(String message, HttpStatus status) {
        // Delegates to the main constructor with null errorCode
        this(message, status, null);
    }
    
    /**
     * Constructs a new API exception with the specified detail message,
     * HTTP status, and error code.
     *
     * @param message   the detail message (which is saved for later retrieval
     *                  by the {@link #getMessage()} method)
     * @param status    the HTTP status code (which is saved for later retrieval
     *                  by the {@link #getStatus()} method)
     * @param errorCode the machine-readable error code (which is saved for later
     *                  retrieval by the {@link #getErrorCode()} method)
     */
    public ApiException(String message, HttpStatus status, String errorCode) {
        // Calls the parent RuntimeException constructor with the message
        super(message);
        
        // Initializes the HTTP status field
        this.status = status;
        
        // Initializes the error code field
        this.errorCode = errorCode;
    }
    
    /**
     * Returns the HTTP status code associated with this exception.
     *
     * @return the HTTP status code
     */
    public HttpStatus getStatus() {
        return status;
    }
    
    /**
     * Returns the error code associated with this exception, if any.
     *
     * @return the error code, or {@code null} if none was specified
     */
    public String getErrorCode() {
        return errorCode;
    }
}