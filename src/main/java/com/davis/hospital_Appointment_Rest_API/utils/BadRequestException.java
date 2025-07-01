package com.davis.hospital_Appointment_Rest_API.utils;

import org.springframework.http.HttpStatus;

//Bad request (400)
public class BadRequestException extends ApiException {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 public BadRequestException(String message) {
     super(message, HttpStatus.BAD_REQUEST, "BAD_REQUEST");
 }
}
