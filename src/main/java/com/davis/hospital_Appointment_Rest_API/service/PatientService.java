package com.davis.hospital_Appointment_Rest_API.service;

import java.util.List;
import com.davis.hospital_Appointment_Rest_API.model.Patient;

/**
 * Service interface for managing {@link Patient} entities.
 * Extends the generic {@link Service} interface with patient-specific operations.
 * 
 * <p>Provides methods for managing patient information including search functionality
 * to find patients by name.</p>
 *
 * @author CYPRIAN DAVIS
 * @since 1.0
 * @see Service
 * @see Patient
 */
public interface PatientService extends Service<Patient> {

    /**
     * Searches for patients whose names match the given search term.
     * 
     * <p>The search implementation may vary but typically performs a case-insensitive
     * partial match (contains) search on patient names.</p>
     *
     * @param name the search term to match against patient names (must not be null or empty)
     * @return a list of matching patients, ordered alphabetically by name.
     *         Returns an empty list if no matches are found (never null)
     * @throws IllegalArgumentException if the name parameter is null or empty
     */
    List<Patient> searchByName(String name);
}