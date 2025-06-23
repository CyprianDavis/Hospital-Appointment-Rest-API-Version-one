package com.davis.hospital_Appointment_Rest_API.utils;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * A generic standardized response format for all API endpoints.
 * @param <T> The type of data payload this response carries
 */
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private LocalDateTime timestamp;
    private T data;
    private Map<String, String> errors;

    // Constructors
    public ApiResponse(boolean success, String message) {
        this(success, message, null, null);
    }

    public ApiResponse(boolean success, String message, T data) {
        this(success, message, data, null);
    }

    public ApiResponse(boolean success, String message, Map<String, String> errors) {
        this(success, message, null, errors);
    }

    private ApiResponse(boolean success, String message, T data, Map<String, String> errors) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.errors = errors;
        this.timestamp = LocalDateTime.now();
    }

    // Getters
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public T getData() { return data; }
    public Map<String, String> getErrors() { return errors; }

    // Factory methods
    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(true, message);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message);
    }

    public static <T> ApiResponse<T> error(String message, Map<String, String> errors) {
        return new ApiResponse<>(false, message, errors);
    }
}