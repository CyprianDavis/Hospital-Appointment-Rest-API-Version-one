package com.davis.hospital_Appointment_Rest_API.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.davis.hospital_Appointment_Rest_API.model.RoleAuthority;
import com.davis.hospital_Appointment_Rest_API.repository.RoleAuthorityRepository;
import com.davis.hospital_Appointment_Rest_API.service.RoleAuthorityService;

/**
 * Implementation of the {@link RoleAuthorityService} interface that handles
 * operations related to Role-Authority relationships in the system.
 * <p>
 * This service manages the many-to-many association between roles and authorities
 * through the {@link RoleAuthority} join entity.
 * 
 * @author CYPRIAN DAVIS
 * @since 1.0
 */
@Service // Marks this as a Spring service component
@Transactional // All methods are transactional by default
public class RoleAuthorityServiceImp implements RoleAuthorityService {

    /**
     * Repository for handling persistence operations of {@link RoleAuthority} entities.
     * Injected automatically by Spring's dependency injection mechanism.
     */
    @Autowired
    private RoleAuthorityRepository roleAuthorityRepository;

    /**
     * Creates and persists a new Role-Authority relationship.
     * 
     * @param roleAuthority The RoleAuthority entity to be created, containing references
     *                      to both Role and Authority. Must not be {@code null}.
     * @return The persisted {@link RoleAuthority} entity with generated ID
     * @throws IllegalArgumentException if the input roleAuthority is {@code null}
     * @throws org.springframework.dao.DataAccessException if there's an issue during persistence
     */
    @Override
    public RoleAuthority addRoleAuthority(RoleAuthority roleAuthority) {
        // Validate input
        if (roleAuthority == null) {
            throw new IllegalArgumentException("RoleAuthority cannot be null");
        }

        // Persist the relationship
        return roleAuthorityRepository.save(roleAuthority);
    }
}