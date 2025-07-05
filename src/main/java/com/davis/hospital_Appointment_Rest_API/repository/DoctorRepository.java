package com.davis.hospital_Appointment_Rest_API.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.davis.hospital_Appointment_Rest_API.dto.ViewDoctor;
import com.davis.hospital_Appointment_Rest_API.model.Doctor;

/**
 * Repository interface for {@link Doctor} entities with DTO projections.
 * 
 * <p>Provides operations for retrieving doctor information as {@link ViewDoctor} DTOs,
 * which contain only the fields needed for display purposes.</p>
 * 
 * <p><b>Key Features:</b>
 * <ul>
 *   <li>Find doctors by specialization (DTO projection)</li>
 *   <li>Search doctors by name fields with DTO results</li>
 *   <li>Uses Spring Data JPA constructor expressions for efficient DTO mapping</li>
 * </ul>
 * </p>
 * 
 * <p><b>Usage Examples:</b>
 * <pre>
 * // Find all cardiologists as ViewDoctor DTOs
 * List<ViewDoctor> cardiologists = doctorRepository.findBySpecialization("Cardiology");
 * 
 * // Search doctors by name components with DTO results
 * List<ViewDoctor> nameMatches = doctorRepository.searchByName("Smith");
 * </pre>
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 2.0
 * @since 2025-06-03
 * @see ViewDoctor
 * @see Doctor
 * @see JpaRepository
 */
public interface DoctorRepository extends JpaRepository<Doctor, String> {

    /**
     * Finds all doctors with the specified medical specialization, returning them as ViewDoctor DTOs.
     * 
     * @param specialization the medical specialization to search for 
     *        (e.g., "Cardiology", "Pediatrics")
     * @return list of ViewDoctor DTOs with matching specialization (empty if none found)
     */
    @Query("SELECT new com.davis.hospital_Appointment_Rest_API.dto.ViewDoctor(" +
           "d.userId, d.surName, d.givenName, d.otherName, d.specialization, " +
           "d.license_number, d.consulation_fee, d.department.name, d.email, d.contact) " +
           "FROM Doctor d WHERE d.specialization = :specialization")
    List<ViewDoctor> findBySpecialization(@Param("specialization") String specialization);
    
    /**
     * Searches for doctors by matching a single name term (case-insensitive, partial match)
     * against the surname, given name, or other name fields, returning ViewDoctor DTOs.
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
     * @return a list of ViewDoctor DTOs whose name fields contain the provided term
     */
    @Query("SELECT new com.davis.hospital_Appointment_Rest_API.dto.ViewDoctor(" +
           "d.userId, d.surName, d.givenName, d.otherName, d.specialization, " +
           "d.license_number, d.consulation_fee, d.department.name, d.email, d.contact) " +
           "FROM Doctor d WHERE " +
           "LOWER(d.surName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
           "LOWER(d.givenName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
           "LOWER(d.otherName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<ViewDoctor> searchByName(@Param("name") String name);

    /**
     * Finds all doctors and returns them as ViewDoctor DTOs.
     * 
     * @return list of all ViewDoctor DTOs in the system
     */
    @Query("SELECT new com.davis.hospital_Appointment_Rest_API.dto.ViewDoctor(" +
           "d.userId, d.surName, d.givenName, d.otherName, d.specialization, " +
           "d.license_number, d.consulation_fee, d.department.name, d.email, d.contact) " +
           "FROM Doctor d")
    List<ViewDoctor> findAllDoctorsAsViewDoctors();
}