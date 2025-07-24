package com.davis.hospital_Appointment_Rest_API.repository;

import java.util.List;
import java.util.Optional;

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
 *   <li>Custom method to find all doctors as DTOs</li>
 *   <li>Find single doctor by ID as DTO</li>
 *   <li>Uses Spring Data JPA constructor expressions for efficient DTO mapping</li>
 * </ul>
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 2.1
 * @since 2025-06-03
 * @see ViewDoctor
 * @see Doctor
 * @see JpaRepository
 */
public interface DoctorRepository extends JpaRepository<Doctor, String> {

    /**
     * Finds all doctors and returns them as ViewDoctor DTOs.
     * <p>
     * This custom method provides better performance than the default findAll()
     * by selecting only the fields needed for display purposes.
     * </p>
     *
     * @return list of all ViewDoctor DTOs in the system (empty if none exist)
     */
    @Query("SELECT new com.davis.hospital_Appointment_Rest_API.dto.ViewDoctor(" +
           "d.userId, d.surName, d.givenName, d.otherName, d.specialization, " +
           "d.license_number, d.consulation_fee, d.department.name, d.email, d.contact) " +
           "FROM Doctor d")
    List<ViewDoctor> findAllDoctorsAsViewDoctors();

    /**
     * Finds a single doctor by ID and returns it as a ViewDoctor DTO.
     * <p>
     * Returns the result as an {@link Optional} to handle cases where no doctor exists
     * with the given ID. The DTO contains only display-optimized fields.
     * </p>
     *
     * @param id the unique identifier of the doctor to find
     * @return {@link Optional} containing the ViewDoctor DTO if found,
     *         or empty Optional if no doctor exists with the given ID
     */
    @Query("SELECT new com.davis.hospital_Appointment_Rest_API.dto.ViewDoctor(" +
           "d.userId, d.surName, d.givenName, d.otherName, d.specialization, " +
           "d.license_number, d.consulation_fee, d.department.name, d.email, d.contact) " +
           "FROM Doctor d WHERE d.userId = :id")
    Optional<ViewDoctor> findDoctorAsViewDoctorById(@Param("id") String id);

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
    
}