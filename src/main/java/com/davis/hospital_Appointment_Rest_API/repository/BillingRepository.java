package com.davis.hospital_Appointment_Rest_API.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.davis.hospital_Appointment_Rest_API.model.Billing;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {
	
	@Query("SELECT b FROM Billing b WHERE "
			+  "LOWER(b.patient.surName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
	           "LOWER(b.patient.givenName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
	           "LOWER(b.patient.otherName) LIKE LOWER(CONCAT('%', :name, '%'))")
	List<Billing> searchByPatientName(@Param("name") String name);
	

}
