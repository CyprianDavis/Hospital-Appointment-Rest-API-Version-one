package com.davis.hospital_Appointment_Rest_API.service;

import java.util.List;
import java.util.Optional;

import com.davis.hospital_Appointment_Rest_API.dto.ViewDoctor;
import com.davis.hospital_Appointment_Rest_API.model.Doctor;

/**
 * Service interface for doctor-related operations in the Hospital Appointment System.
 * <p>
 * Provides business logic for doctor management and search operations, returning
 * {@link ViewDoctor} DTOs optimized for display purposes. This interface extends
 * the generic {@link Service} interface with doctor-specific functionality.
 * </p>
 *
 * <p><b>Key Features:</b>
 * <ul>
 *   <li>Search doctors by medical specialization</li>
 *   <li>Search doctors by name components</li>
 *   <li>Returns lightweight {@link ViewDoctor} DTOs instead of full entities</li>
 *   <li>Inherits standard CRUD operations from {@link Service}</li>
 * </ul>
 * </p>
 *
 * <p><b>Usage Guidelines:</b>
 * <ul>
 *   <li>Use for all doctor-related business logic</li>
 *   <li>Prefer these methods over direct repository access for business operations</li>
 *   <li>DTO results are optimized for API responses and UI display</li>
 * </ul>
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 2.0
 * @since 2025-06-03
 * @see ViewDoctor
 * @see Service
 */
public interface DoctorService extends Service<Doctor> {

    /**
     * Retrieves a doctor's view information by their unique identifier.
     * <p>
     * Returns a {@link ViewDoctor} DTO containing only display-optimized fields,
     * wrapped in an {@link Optional} to handle cases where no doctor exists with
     * the given ID. The DTO excludes sensitive information and includes only
     * fields needed for display purposes.
     * </p>
     *
     * @param id the unique identifier of the doctor to find (must not be null or empty)
     * @return {@link Optional} containing the {@link ViewDoctor} DTO if found,
     *         or empty Optional if no doctor exists with the given ID
     * @throws IllegalArgumentException if the id parameter is null or empty
     */
    Optional<ViewDoctor> findDoctorAsViewDoctorById(String id);

    /**
     * Retrieves all doctors as lightweight {@link ViewDoctor} DTOs.
     * <p>
     * This optimized version returns only the fields needed for display purposes,
     * providing better performance than the entity-based alternatives. The DTOs
     * contain all essential doctor information without the overhead of loading
     * full entity relationships.
     * </p>
     *
     * @return list of all {@link ViewDoctor} DTOs in the system;
     *         empty list if no doctors exist (never null)
     */
    List<ViewDoctor> findAllDoctorsAsViewDoctors();
    
    /**
     * Finds doctors by their medical specialization and returns them as {@link ViewDoctor} DTOs.
     * <p>
     * Searches for doctors whose specialization exactly matches the provided parameter.
     * The search is case-sensitive and requires an exact match of the specialization name.
     * </p>
     *
     * <p><b>Example:</b>
     * <pre>
     * // Find all pediatricians
     * List<ViewDoctor> pediatricians = doctorService.searchBySpecialization("Pediatrics");
     * </pre>
     * </p>
     *
     * @param specialization the medical specialization to search for (e.g., "Cardiology", "Pediatrics")
     * @return a list of {@link ViewDoctor} DTOs with matching specialization;
     *         empty list if no matches found (never null)
     * @throws IllegalArgumentException if specialization parameter is null or empty
     */
    List<ViewDoctor> searchBySpecialization(String specialization);
    
    /**
     * Searches for doctors by name components and returns them as {@link ViewDoctor} DTOs.
     * <p>
     * Performs a case-insensitive partial match search against:
     * <ul>
     *   <li>Surname (family name)</li>
     *   <li>Given name (first name)</li>
     *   <li>Other names (middle names)</li>
     * </ul>
     * The search term can match any part of these name fields.
     * </p>
     *
     * <p><b>Examples:</b>
     * <pre>
     * // Search for doctors with "Smith" in any name field
     * List<ViewDoctor> results = doctorService.searchByNames("Smith");
     *
     * // Search for doctors with "John" or "Jon" in any name field
     * List<ViewDoctor> results = doctorService.searchByNames("Jon");
     * </pre>
     * </p>
     *
     * @param names the search term to match against name fields (partial, case-insensitive)
     * @return a list of {@link ViewDoctor} DTOs whose names contain the search term;
     *         empty list if no matches found (never null)
     * @throws IllegalArgumentException if names parameter is null or empty
     */
    List<ViewDoctor> searchByNames(String names);
}