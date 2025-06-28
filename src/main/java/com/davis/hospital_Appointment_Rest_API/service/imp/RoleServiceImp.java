package com.davis.hospital_Appointment_Rest_API.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davis.hospital_Appointment_Rest_API.model.Role;
import com.davis.hospital_Appointment_Rest_API.repository.RoleRepository;
import com.davis.hospital_Appointment_Rest_API.service.RoleService;

/**
 * Implementation of the {@link RoleService} interface that provides
 * CRUD operations for {@link Role} entities.
 * <p>
 * This service handles all business logic related to role management,
 * delegating persistence operations to the {@link RoleRepository}.
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @see RoleService
 * @see Role
 * @see RoleRepository
 */
@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Retrieves all roles from the database.
     *
     * @return a list of all roles (empty list if no roles found)
     */
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    /**
     * Saves a role entity to the database.
     * <p>
     * Can be used for both creating new roles and updating existing ones.
     * </p>
     *
     * @param role the role entity to be saved (must not be null)
     * @return the saved role entity
     * @throws IllegalArgumentException if the role parameter is null
     */
    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    /**
     * Finds a role by its name (case-insensitive search).
     *
     * @param name the name of the role to search for (case-insensitive)
     * @return the role with the given name, or null if not found
     * @throws IllegalArgumentException if the name parameter is null
     */
    @Override
    public Role findByName(String name) {
        return roleRepository.findByNameIgnoreCase(name);
    }

    /**
     * Finds a role by its unique identifier.
     *
     * @param id the ID of the role to search for (must not be null)
     * @return an {@link Optional} containing the role if found,
     *         or empty Optional if no role with the given ID exists
     * @throws IllegalArgumentException if the id parameter is null
     */
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }
}