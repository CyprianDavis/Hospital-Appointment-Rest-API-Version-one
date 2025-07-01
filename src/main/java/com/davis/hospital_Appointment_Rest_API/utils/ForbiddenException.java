package com.davis.hospital_Appointment_Rest_API.utils;

import org.springframework.http.HttpStatus;

//Forbidden (403)
public class ForbiddenException extends ApiException {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 public ForbiddenException(String message) {
     super(message, HttpStatus.FORBIDDEN, "FORBIDDEN");
 }
}