package com.davis.hospital_Appointment_Rest_API.utils;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldValue), 
              HttpStatus.NOT_FOUND,
              "RESOURCE_NOT_FOUND");
    }
}