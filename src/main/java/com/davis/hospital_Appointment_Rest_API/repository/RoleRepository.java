package com.davis.hospital_Appointment_Rest_API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.davis.hospital_Appointment_Rest_API.model.Role;

/**
 * Repository interface for {@link Role} entity operations.
 * <p>
 * Provides CRUD functionality and custom query methods for role management
 * in the hospital appointment system. Extends {@link JpaRepository} to inherit
 * standard data access operations.
 * </p>
 * 
 * <p><b>Key Features:</b></p>
 * <ul>
 *   <li>Standard CRUD operations (inherited from JpaRepository)</li>
 *   <li>Case-insensitive role name lookup</li>
 *   <li>Automatic query method implementation</li>
 * </ul>
 * 
 * @see Role
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2023-06-15
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Finds a role by its name (case-insensitive match).
     * <p>
     * Spring Data JPA will automatically implement this method to perform
     * a case-insensitive search for roles matching the specified name.
     * </p>
     * 
     * <p><b>Usage Example:</b></p>
     * <pre>
     * Role adminRole = roleRepository.findByNameIgnoreCase("admin");
     * </pre>
     * 
     * @param name The role name to search for (case doesn't matter)
     * @return The matching {@link Role} or {@code null} if not found
     * @see Role#getName()
     */
    Role findByNameIgnoreCase(String name);
}
