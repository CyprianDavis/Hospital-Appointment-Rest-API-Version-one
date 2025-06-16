package com.davis.hospital_Appointment_Rest_API.service;

import java.util.Optional;

import com.davis.hospital_Appointment_Rest_API.model.Department;

/**
 * Service interface for managing {@link Department} entities.
 * Extends the generic {@link Service} interface with department-specific operations.
 * Provides methods for retrieving departments by name and location code.
 * 
 * @param <Department> the department entity type
 * @param <String> the type of department identifier
 * @author CYPRIAN DAVIS
 */
public interface DepartmentService extends Service<Department> {

    /**
     * Retrieves a department by its exact name.
     *
     * @param department the name of the department to search for (case-sensitive)
     * @return the department with the specified name, or {@code null} if not found
     * @throws IllegalArgumentException if the department name parameter is null or empty
     */
    Optional<Department> findByName(String department);

    /**
     * Retrieves a department by its unique location code.
     *
     * @param code the location code to search for
     * @return the department with the specified location code, or {@code null} if not found
     * @throws IllegalArgumentException if the location code parameter is null or empty
     */
     Optional<Department> findByLocationCode(String code);
}