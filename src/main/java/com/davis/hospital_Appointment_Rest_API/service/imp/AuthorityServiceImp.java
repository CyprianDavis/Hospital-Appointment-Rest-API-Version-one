package com.davis.hospital_Appointment_Rest_API.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davis.hospital_Appointment_Rest_API.model.Authority;
import com.davis.hospital_Appointment_Rest_API.repository.AuthorityRepository;
import com.davis.hospital_Appointment_Rest_API.service.AuthorityService;

/**
 * Implementation of the {@link AuthorityService} interface that provides
 * CRUD operations for {@link Authority} entities.
 * 
 * @author CYPRIAN DAVIS
 * @see AuthorityService
 * @see Authority
 * @see AuthorityRepository
 */
@Service
public class AuthorityServiceImp implements AuthorityService {
    
    @Autowired
    private AuthorityRepository authorityRepository;

    /**
     * Retrieves all authorities from the database.
     *
     * @return a list of all authorities
     */
    @Override
    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

    /**
     * Saves a given authority to the database.
     * 
     * @param authority the authority to be saved
     * @return the saved authority entity
     * @throws IllegalArgumentException if the provided authority is null
     */
    @Override
    public Authority save(Authority authority) {
        return authorityRepository.save(authority);
    }

    /**
     * Finds an authority by its name (case-insensitive search).
     *
     * @param name the name of the authority to search for
     * @return the authority with the given name, or null if not found
     */
    @Override
    public Authority findByName(String name) {
        return authorityRepository.findByNameIgnoreCase(name);
    }

    /**
     * Finds an authority by its unique identifier.
     *
     * @param id the ID of the authority to search for
     * @return an {@link Optional} containing the authority if found,
     *         or empty Optional if not found
     * @throws IllegalArgumentException if the provided id is null
     */
    
    public Optional<Authority> findById(Long id) {
       
        return authorityRepository.findById(id);
    }

}