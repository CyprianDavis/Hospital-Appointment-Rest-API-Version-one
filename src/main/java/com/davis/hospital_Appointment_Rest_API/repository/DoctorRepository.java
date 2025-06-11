package com.davis.hospital_Appointment_Rest_API.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.davis.hospital_Appointment_Rest_API.model.Doctor;

/**
 * Repository interface for {@link Doctor} entities.
 * 
 * <p>Provides CRUD operations and custom queries for managing doctor information,
 * including search capabilities by specialization and name fields.</p>
 * 
 * <p><b>Key Features:</b>
 * <ul>
 *   <li>Find doctors by medical specialization</li>
 *   <li>Search doctors by name fields (surname, given name, or other name)</li>
 *   <li>Inherits all standard JPA repository operations</li>
 * </ul>
 * </p>
 * 
 * <p><b>Usage Examples:</b>
 * <pre>
 * // Find all cardiologists
 * List<Doctor> cardiologists = doctorRepository.findBySpecialization("Cardiology");
 * 
 * // Search doctors by name components
 * List<Doctor> nameMatches = doctorRepository.findBySurNameOrGivenNameOrOtherName(
 *     "Smith", "John", null);
 * </pre>
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-03
 * @see Doctor
 * @see JpaRepository
 */
public interface DoctorRepository extends JpaRepository<Doctor, String> {

    /**
     * Finds all doctors with the specified medical specialization.
     * 
     * @param specialization the medical specialization to search for 
     *        (e.g., "Cardiology", "Pediatrics")
     * @return list of doctors with matching specialization (empty if none found)
     */
    List<Doctor> findBySpecialization(String specialization);
    
   

    /**
     * Searches for doctors by matching a single name term (case-insensitive, partial match)
     * against the surname, given name, or other name fields.
     *
     * <p>
     * The search term is matched using partial string comparison on the following fields:
     * <ul>
     *   <li>Surname (family name)</li>
     *   <li>Given name (first name)</li>
     *   <li>Other name (middle name)</li>
     * </ul>
     * Matching is case-insensitive and allows the term to appear anywhere within the name fields.
     * </p>
     *
     * @param name the name term to match (partial, case-insensitive)
     * @return a list of doctors whose name fields contain the provided term
     */
    @Query("SELECT d FROM Doctor d WHERE " +
           "LOWER(d.surName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
           "LOWER(d.givenName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
           "LOWER(d.otherName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Doctor> searchByName(@Param("name") String name);


}
