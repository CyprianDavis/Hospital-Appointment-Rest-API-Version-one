package com.davis.hospital_Appointment_Rest_API.service;

import java.util.List;

import com.davis.hospital_Appointment_Rest_API.dto.PatientDto;
import com.davis.hospital_Appointment_Rest_API.model.Patient;

/**
 * Service interface for patient management operations.
 * <p>
 * Extends the generic {@link Service} interface with patient-specific functionality
 * including comprehensive search capabilities and DTO-based data transfer.
 * </p>
 *
 * <p><b>Key Features:</b>
 * <ul>
 *   <li>CRUD operations inherited from {@link Service}</li>
 *   <li>Name-based patient search functionality</li>
 *   <li>DTO-based data transfer for API consumption</li>
 *   <li>Pagination support for large result sets</li>
 * </ul>
 * </p>
 *
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-03
 * @see Service
 * @see Patient
 * @see PatientDto
 */
public interface PatientService extends Service<Patient> {

    /**
     * Searches for patients by name using entity objects.
     * <p>
     * Performs a case-insensitive search across all name fields (surname, given name, other names)
     * and returns full patient entities. This method is suitable for internal processing
     * where complete patient data is required.
     * </p>
     *
     * @param name the search term to match against patient names (must not be {@code null} or empty)
     * @return an ordered list of matching {@link Patient} entities, sorted alphabetically by surname.
     *         Returns an empty list if no matches are found (never {@code null})
     * @throws IllegalArgumentException if name parameter is {@code null} or empty
     * @deprecated Prefer {@link #searchPatientByName(String)} for API-facing operations
     */
    @Deprecated
    List<Patient> searchByName(String name);

    /**
     * Searches for patients by name using DTO projection.
     * <p>
     * Performs a case-insensitive, partial-match search across all name fields
     * and returns lightweight {@link PatientDto} objects suitable for API responses.
     * Combines name components into a single formatted name field in the DTO.
     * </p>
     *
     * @param name the search term to match against patient names (must not be {@code null} or empty)
     * @return a list of matching {@link PatientDto} objects, ordered by:
     *         <ol>
     *           <li>Surname (ascending)</li>
     *           <li>Given name (ascending)</li>
     *         </ol>
     *         Returns an empty list if no matches are found (never {@code null})
     * @throws IllegalArgumentException if name parameter is {@code null} or empty
     */
    List<PatientDto> searchPatientByName(String name);

    /**
     * Retrieves all patients in the system as DTOs.
     * <p>
     * Returns a list of all patients converted to {@link PatientDto} format,
     * combining name components into a single field and including only relevant
     * fields for API consumption.
     * </p>
     *
     * @return a list of all {@link PatientDto} objects in the system,
     *         ordered by:
     *         <ol>
     *           <li>Surname (ascending)</li>
     *           <li>Given name (ascending)</li>
     *         </ol>
     *         Returns an empty list if no patients exist (never {@code null})
     */
    List<PatientDto> findAllPatients();

    /**
     * Retrieves paginated list of patients as DTOs.
     * <p>
     * Returns a subset of patients for efficient data transfer and display,
     * particularly useful for large datasets.
     * </p>
     *
     * @param pageNumber the zero-based page index (must not be negative)
     * @param pageSize the size of the page to be returned (must be positive)
     * @return a paginated list of {@link PatientDto} objects
     * @throws IllegalArgumentException if pageNumber is negative or pageSize is not positive
     */
    List<PatientDto> findAllPatients(int pageNumber, int pageSize);
}