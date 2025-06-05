package com.davis.hospital_Appointment_Rest_API.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.davis.hospital_Appointment_Rest_API.model.User;
import com.davis.hospital_Appointment_Rest_API.repository.UserRepository;

/**
 * Service layer component for managing {@link User} entities.
 * <p>
 * This class provides business logic operations for user management,
 * acting as an intermediary between controllers and the repository layer.
 * It handles operations such as retrieving all users and adding new users.
 * </p>
 * 
 * <p><b>Service Responsibilities:</b>
 * <ul>
 *   <li>Retrieve user information</li>
 *   <li>Create new user accounts</li>
 *   <li>Serve as a transaction boundary for user operations</li>
 * </ul>
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-03
 * @see User
 * @see UserRepository
 */
@Service
public class UserService {
    
    /**
     * The repository for performing CRUD operations on {@link User} entities.
     * <p>
     * Injected automatically by Spring's dependency injection mechanism.
     * This repository provides methods for interacting with the database
     * where user data is persisted.
     * </p>
     */
    @Autowired
    private UserRepository userRepository;
    
    /**
     * Retrieves all users from the system.
     * <p>
     * This method fetches a complete list of all user entities currently
     * stored in the database, regardless of their specific type (Patient,
     * Doctor, or Admin).
     * </p>
     * 
     * @return a {@link List} of all {@link User} entities in the system.
     *         Returns an empty list if no users exist.
     * 
     * @see UserRepository#findAll()
     */
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    
    /**
     * Adds a new user to the system.
     * <p>
     * Persists the provided user entity to the database. The implementation
     * handles all user subtypes (Patient, Doctor, Admin) through JPA inheritance.
     * </p>
     * 
     * @param user the {@link User} entity to be persisted. Must not be {@code null}.
     * @return the persisted {@link User} entity with generated identifier fields
     *         (if any) populated.
     * @throws IllegalArgumentException if the provided user is {@code null}.
     * @throws org.springframework.dao.DataAccessException if there's an issue
     *         during database access.
     * 
     * @see UserRepository#save(Object)
     */
    public User addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        return userRepository.save(user);
    }
}