package com.davis.hospital_Appointment_Rest_API.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when authentication is required and has failed or has not been provided.
 * <p>
 * Indicates that the request lacks valid authentication credentials for the
 * target resource. Returns a 401 Unauthorized status code.
 * </p>
 * 
 * <p>
 * Common scenarios include:
 * <ul>
 *   <li>Missing or invalid authentication token</li>
 *   <li>Expired credentials</li>
 *   <li>Failed login attempts</li>
 * </ul>
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @see ApiException
 * @see HttpStatus#UNAUTHORIZED
 * @since 2025-07-01
 */
public class UnauthorizedException extends ApiException {
    
    /**
     * Serial version UID for serialization compatibility.
     * <p>
     * Maintains version control for serialized instances of this exception.
     * Required for all {@link java.io.Serializable} implementations.
     * </p>
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new UnauthorizedException with the specified detail message.
     * <p>
     * The message should clearly indicate the reason for the authentication failure.
     * </p>
     *
     * @param message the detail message that explains why authentication failed.
     *        Should be descriptive enough to help API consumers correct the issue.
     *        Examples:
     *        <ul>
     *          <li>"Invalid credentials"</li>
     *          <li>"Session expired"</li>
     *        </ul>
     * 
     * @implNote
     * This constructor:
     * <ol>
     *   <li>Sets HTTP status code to 401 (UNAUTHORIZED)</li>
     *   <li>Sets standard error code to "UNAUTHORIZED"</li>
     *   <li>Preserves the stack trace</li>
     * </ol>
     * 
     * 
     * }</pre>
     */
    public UnauthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");
    }
}