package com.davis.hospital_Appointment_Rest_API.utils;

import java.time.LocalDate;

public record AppointmentRequest(String patientId,String doctorSpecialty,
		LocalDate preferredDate) {

}
