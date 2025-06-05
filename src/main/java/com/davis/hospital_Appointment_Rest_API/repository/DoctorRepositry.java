package com.davis.hospital_Appointment_Rest_API.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davis.hospital_Appointment_Rest_API.model.Doctor;

public interface DoctorRepositry extends JpaRepository<Doctor, String>{

}
