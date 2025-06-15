package com.davis.hospital_Appointment_Rest_API.service;

import com.davis.hospital_Appointment_Rest_API.model.Authority;

/**
 * Service interface for managing {@link Authority} entities.
 * Extends the generic {@link Service} interface with authority-specific operations.
 * 
 * <p>Provides methods for managing system authorities/roles which define
 * access control permissions within the application.</p>
 *
 * @author CYPRIAN DAVIS
 * @since 1.0
 * @see Service
 * @see Authority
 */
public interface AuthorityService extends Service<Authority> {

    /**
     * Finds an authority by its exact name.
     * 
     * <p>This method performs a case-sensitive search for a specific authority
     * (e.g., "ROLE_ADMIN", "ROLE_DOCTOR").</p>
     *
     * @param name the exact name of the authority to retrieve (case-sensitive)
     * @return the authority entity if found, or {@code null} if no authority exists
     *         with the specified name
     * @throws IllegalArgumentException if the name parameter is null or empty
     */
    Authority findByName(String name);
}