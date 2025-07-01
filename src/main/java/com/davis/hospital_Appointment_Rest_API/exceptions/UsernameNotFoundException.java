package com.davis.hospital_Appointment_Rest_API.exceptions;

public class UsernameNotFoundException extends RuntimeException{

	public UsernameNotFoundException(String message)
	{
		super(message);
	}
}
