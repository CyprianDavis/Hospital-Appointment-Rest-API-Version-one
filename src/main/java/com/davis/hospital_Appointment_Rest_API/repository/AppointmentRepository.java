package com.davis.hospital_Appointment_Rest_API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davis.hospital_Appointment_Rest_API.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

	
}
