package com.davis.hospital_Appointment_Rest_API.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.davis.hospital_Appointment_Rest_API.model.Patient;

/**
 * Repository interface for {@link Patient} entities.
 * 
 * <p>Provides CRUD operations and custom queries for managing patient information,
 * including comprehensive name search functionality.</p>
 * 
 * <p><b>Key Features:</b>
 * <ul>
 *   <li>Search patients by name fields (surname, given name, or other name)</li>
 *   <li>Case-insensitive partial matching for name searches</li>
 *   <li>Inherits all standard JPA repository operations</li>
 * </ul>
 * </p>
 * 
 * <p><b>Usage Example:</b>
 * <pre>
 * // Search for patients with names containing "smith"
 * List<Patient> results = patientRepository.searchByName("smith");
 * </pre>
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-03
 * @see Patient
 * @see JpaRepository
 */
public interface PatientRepository extends JpaRepository<Patient, String> {

	@Query("SELECT p FROM Patient p WHERE " +
		       "LOWER(p.surName) LIKE LOWER(CONCAT('%', :nameTerm, '%')) OR " +
		       "LOWER(p.givenName) LIKE LOWER(CONCAT('%', :nameTerm, '%')) OR " +
		       "LOWER(p.otherName) LIKE LOWER(CONCAT('%', :nameTerm, '%'))")
		List<Patient> searchByName(@Param("nameTerm") String nameTerm);

}