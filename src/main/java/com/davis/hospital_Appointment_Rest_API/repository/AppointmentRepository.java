package com.davis.hospital_Appointment_Rest_API.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.davis.hospital_Appointment_Rest_API.model.Appointment;
/**
 * @author CYPRIAN DAVIS
 */

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
	
	@Query("SELECT a FROM Appointment a WHERE "
			+  "LOWER(d.patient.surName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
	           "LOWER(d.patient.givenName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
	           "LOWER(d.patient.otherName) LIKE LOWER(CONCAT('%', :name, '%'))")
	List<Appointment> searchByPatientName(@Param("name")String name);
	
	@Query("SELECT a FROM Appointment a WHERE "
			+  "LOWER(d.doctor.surName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
	           "LOWER(d.doctor.givenName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
	           "LOWER(d.doctor.otherName) LIKE LOWER(CONCAT('%', :name, '%'))")
	List<Appointment> searchByDoctorName(@Param("name")String name);
	
	

	
}
