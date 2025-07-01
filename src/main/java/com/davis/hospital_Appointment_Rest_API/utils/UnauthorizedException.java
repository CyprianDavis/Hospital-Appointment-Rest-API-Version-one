package com.davis.hospital_Appointment_Rest_API.utils;

import org.springframework.http.HttpStatus;

//Unauthorized (401)
public class UnauthorizedException extends ApiException {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 public UnauthorizedException(String message) {
     super(message, HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");
 }
}