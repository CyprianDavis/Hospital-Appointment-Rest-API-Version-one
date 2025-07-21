package com.davis.hospital_Appointment_Rest_API.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.davis.hospital_Appointment_Rest_API.dto.PatientDto;
import com.davis.hospital_Appointment_Rest_API.model.Patient;

/**
 * Repository interface for {@link Patient} entities with DTO-based query methods.
 * 
 * <p>Extends standard JPA repository functionality with custom queries that return
 * {@link PatientDto} objects for safer data transfer between layers.</p>
 * 
 * <p><b>Key Features:</b>
 * <ul>
 *   <li>DTO-based projection queries for patient data</li>
 *   <li>Comprehensive name search returning DTOs</li>
 *   <li>Case-insensitive partial matching for name searches</li>
 *   <li>Efficient data transfer with only necessary fields</li>
 * </ul>
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-07-22
 * @see Patient
 * @see PatientDto
 * @see JpaRepository
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

    /**
     * Searches for patients by name fields and returns results as DTOs.
     * <p>
     * Searches across surname, given name, and other name fields using case-insensitive
     * partial matching. Combines the name components into a single field in the DTO.
     * </p>
     * 
     * @param nameTerm The search term to match against patient names
     * @return List of PatientDto objects containing matching patients
     */
    @Query("SELECT new com.davis.hospital_Appointment_Rest_API.dto.PatientDto(" +
           "p.userName, " +
           "CONCAT(p.surName, ' ', p.givenName, " +
           "p.otherName), " +
           "p.bloodGroup, p.contact, p.email, p.postalCode, p.gender, p.dateOfBirth) " +
           "FROM Patient p WHERE " +
           "LOWER(p.surName) LIKE LOWER(CONCAT('%', :nameTerm, '%')) OR " +
           "LOWER(p.givenName) LIKE LOWER(CONCAT('%', :nameTerm, '%')) OR " +
           "LOWER(p.otherName) LIKE LOWER(CONCAT('%', :nameTerm, '%'))")
    List<PatientDto> searchPatientByName(@Param("nameTerm") String nameTerm);

    /**
     * Finds all patients and returns them as DTOs.
     * <p>
     * Retrieves all patients from the database and converts them to DTO format,
     * combining name components into a single field.
     * </p>
     * 
     * @return List of PatientDto objects for all patients
     */
    @Query("SELECT new com.davis.hospital_Appointment_Rest_API.dto.PatientDto(" +
           "p.userName, " +
           "CONCAT(p.surName, ' ', p.givenName, " +
           "p.otherName), " +
           "p.bloodGroup, p.contact, p.email, p.postalCode, p.gender, p.dateOfBirth) " +
           "FROM Patient p")
    List<PatientDto> findAllPatients();
    
    /**
     * Alternative search method that returns entities (for backward compatibility)
     * 
     * @param nameTerm The search term to match against patient names
     * @return List of Patient entities containing matching patients
     * @deprecated Use {@link #searchPatientByName(String)} for DTO projection instead
     */
    @Deprecated
    @Query("SELECT p FROM Patient p WHERE " +
           "LOWER(p.surName) LIKE LOWER(CONCAT('%', :nameTerm, '%')) OR " +
           "LOWER(p.givenName) LIKE LOWER(CONCAT('%', :nameTerm, '%')) OR " +
           "LOWER(p.otherName) LIKE LOWER(CONCAT('%', :nameTerm, '%'))")
    List<Patient> searchByName(@Param("nameTerm") String nameTerm);
}