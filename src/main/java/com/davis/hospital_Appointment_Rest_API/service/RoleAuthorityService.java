package com.davis.hospital_Appointment_Rest_API.service;

import com.davis.hospital_Appointment_Rest_API.model.RoleAuthority;

/**
 * Service interface for managing Role-Authority relationships in the system.
 * <p>
 * Defines operations for creating and managing associations between roles and authorities
 * through the {@link RoleAuthority} join entity.
 * 
 * @author CYPRIAN DAVIS
 * @since 1.0
 */
public interface RoleAuthorityService {

    /**
     * Creates a new association between a Role and an Authority.
     * <p>
     * This establishes a many-to-many relationship between the specified role and authority
     * by persisting a new {@link RoleAuthority} entity.
     *
     * @param roleAuthority The RoleAuthority entity containing the role-authority association to be created.
     *                      Must contain valid references to both Role and Authority entities.
     *                      Must not be {@code null}.
     * @return The persisted {@link RoleAuthority} entity with generated identifier
     * @throws IllegalArgumentException if roleAuthority is {@code null} 
     *         or contains invalid references
     * @throws org.springframework.dao.DataAccessException if there's a persistence error
     */
    RoleAuthority addRoleAuthority(RoleAuthority roleAuthority);
}