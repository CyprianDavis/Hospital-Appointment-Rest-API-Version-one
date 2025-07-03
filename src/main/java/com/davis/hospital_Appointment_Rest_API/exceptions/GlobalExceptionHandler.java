package com.davis.hospital_Appointment_Rest_API.exceptions;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
 *   <li>{@link ApiException} - Base custom API exceptions</li>
 *   <li>{@link BadRequestException} - Invalid client requests (400)</li>
 *   <li>{@link ResourceNotFoundException} - Missing resources (404)</li>
 *   <li>{@link UnauthorizedException} - Authentication failures (401)</li>
 *   <li>{@link ForbiddenException} - Authorization failures (403)</li>
 *   <li>{@link MethodArgumentNotValidException} - Validation failures (400)</li>
 *   <li>Spring Security exceptions - Authentication/Authorization failures</li>
 *   <li>{@link Exception} - All other unexpected exceptions (500)</li>
 * </ul>
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-07-01
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles invalid credentials during authentication
     * @param ex The BadCredentialsException instance
     * @return ApiResponse with UNAUTHORIZED status (401)
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadCredentialsException(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.error("Invalid username or password"));
    }

    /**
     * Handles cases where authentication is insufficient
     * @param ex The InsufficientAuthenticationException instance
     * @return ApiResponse with UNAUTHORIZED status (401)
     */
    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<ApiResponse<Void>> handleInsufficientAuthenticationException(
            InsufficientAuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.error("Authentication required. Please provide valid credentials"));
    }

    /**
     * Handles authorization failures when access is denied
     * @param ex The AuthorizationDeniedException instance
     * @return ApiResponse with FORBIDDEN status (403)
     */
    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ApiResponse<Void>> handleAccessDeniedException(AuthorizationDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ApiResponse.error("You don't have permission to access this resource"));
    }

    /**
     * Handles general authentication failures
     * @param ex The AuthenticationException instance
     * @return ApiResponse with UNAUTHORIZED status (401)
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<Void>> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.error("Authentication failed: " + ex.getMessage()));
    }

    /**
     * Handles validation failures for method arguments
     * @param ex The MethodArgumentNotValidException instance
     * @return ApiResponse with BAD_REQUEST status (400) including field errors
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        fieldError -> fieldError.getDefaultMessage() != null ? 
                                fieldError.getDefaultMessage() : "Validation error"));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error("Validation failed", errors));
    }

    /**
     * Handles custom API exceptions
     * @param ex The ApiException instance
     * @return ApiResponse with the specified status code from the exception
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<Void>> handleApiException(ApiException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(ApiResponse.error(ex.getMessage()));
    }

    /**
     * Handles malformed or invalid client requests
     * @param ex The BadRequestException instance
     * @return ApiResponse with BAD_REQUEST status (400)
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(ex.getMessage()));
    }

    /**
     * Handles requests for non-existent resources
     * @param ex The ResourceNotFoundException instance
     * @return ApiResponse with NOT_FOUND status (404)
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(ex.getMessage()));
    }

    /**
     * Handles unauthorized access attempts
     * @param ex The UnauthorizedException instance
     * @return ApiResponse with UNAUTHORIZED status (401)
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<Void>> handleUnauthorizedException(UnauthorizedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.error(ex.getMessage()));
    }

    /**
     * Handles forbidden access attempts
     * @param ex The ForbiddenException instance
     * @return ApiResponse with FORBIDDEN status (403)
     */
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiResponse<Void>> handleForbiddenException(ForbiddenException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ApiResponse.error(ex.getMessage()));
    }

    /**
     * Fallback handler for all other uncaught exceptions
     * @param ex The Exception instance
     * @return ApiResponse with INTERNAL_SERVER_ERROR status (500)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneralException(Exception ex) {
        // Log the full exception for debugging
        ex.printStackTrace();
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("An unexpected error occurred: " + ex.getMessage()));
    }
}