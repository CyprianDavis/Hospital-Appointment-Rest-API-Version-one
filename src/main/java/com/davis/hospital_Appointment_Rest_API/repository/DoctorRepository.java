package com.davis.hospital_Appointment_Rest_API.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
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
     * Finds doctors matching any of the provided name components.
     * 
     * <p>Search is performed across three name fields:
     * <ul>
     *   <li>Surname (family name)</li>
     *   <li>Given name (first name)</li>
     *   <li>Other name (middle name)</li>
     * </ul>
     * </p>
     * 
     * @param surName the surname to match (can be null)
     * @param givenName the given name to match (can be null)
     * @param otherName the other name to match (can be null)
     * @return list of doctors matching any of the provided name components
     *         (empty if no matches found)
     */
    List<Doctor> findBySurNameOrGivenNameOrOtherName(String surName, String givenName, String otherName);
}