package com.davis.hospital_Appointment_Rest_API.service;

import com.davis.hospital_Appointment_Rest_API.model.Role;

/**
 * Service interface for managing {@link Role} entities.
 * Extends the generic {@link Service} interface with role-specific operations.
 * 
 * @author CYPRIAN DAVIS
 * @since 1.0
 * @see Service
 * @see Role
 */
public interface RoleService extends Service<Role> {

    /**
     * Finds a role by its exact name.
     * 
     * @param name the name of the role to search for (case-sensitive)
     * @return the found role entity, or {@code null} if no role was found
     * @throws IllegalArgumentException if the name parameter is null or empty
     */
    Role findByName(String name);
}