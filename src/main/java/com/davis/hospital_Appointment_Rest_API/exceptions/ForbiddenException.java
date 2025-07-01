package com.davis.hospital_Appointment_Rest_API.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when a client has valid credentials but not enough privileges
 * to access the requested resource.
 * <p>
 * Returns HTTP status code 403 (FORBIDDEN) indicating the server understood
 * the request but refuses to authorize it.
 * </p>
 *
 * <p>
 * Typical usage scenarios include:
 * <ul>
 *   <li>User lacks required role/permission</li>
 *   <li>Attempt to access another user's private data</li>
 *   <li>Disabled account trying to access resources</li>
 * </ul>
 * </p>
 *
 * @author Davis Developer
 * @version 1.0
 * @see ApiException
 * @see HttpStatus#FORBIDDEN
 * @since 2025-07-01
 */
public class ForbiddenException extends ApiException {
    
    /**
     * Serial version UID for serialization compatibility.
     * <p>
     * Required field for all {@link java.io.Serializable} implementations
     * to maintain version control of serialized instances.
     * </p>
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new ForbiddenException with the specified detail message.
     * <p>
     * The message should clearly explain the authorization failure without
     * exposing sensitive security information.
     * </p>
     *
     * @param message the detail message explaining the authorization failure.
     *        Should be informative but not reveal system internals.
     *        Examples:
     *        <ul>
     *          <li>"Insufficient privileges to access this resource"</li>
     *          <li>"Account disabled"</li>
     *          <li>"Access restricted to administrators"</li>
     *        </ul>
     *
     * @implNote
     * This constructor:
     * <ol>
     *   <li>Sets HTTP status code to 403 (FORBIDDEN)</li>
     *   <li>Sets standard error code to "FORBIDDEN"</li>
     *   <li>Maintains the exception stack trace</li>
     * </ol>
     *
     * @example
     * <pre>{@code
     * if (!user.hasRole("ADMIN")) {
     *     throw new ForbiddenException("Administrator privileges required");
     * }
     * }</pre>
     */
    public ForbiddenException(String message) {
        super(message, HttpStatus.FORBIDDEN, "FORBIDDEN");
    }
}