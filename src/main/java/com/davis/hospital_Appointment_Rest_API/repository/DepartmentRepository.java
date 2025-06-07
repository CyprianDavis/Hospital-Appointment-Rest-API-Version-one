package com.davis.hospital_Appointment_Rest_API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.davis.hospital_Appointment_Rest_API.model.Department;
import java.util.Optional;

/**
 * Repository interface for {@link Department} entities.
 * Provides CRUD operations and custom query methods for Department management.
 * @author CYPRIAN DAVIS
 */
public interface DepartmentRepository extends JpaRepository<Department, String> {
    
    /**
     * Finds a department by its name (case-insensitive match).
     *
     * @param name the name of the department to search for
     * @return an {@link Optional} containing the matching department if found, 
     *         or empty if no match exists
     */
    Optional<Department> findByNameIgnoreCase(String name);
    
    /**
     * Finds a department by its location code.
     *
     * @param locationCode the unique location code identifier
     * @return an {@link Optional} containing the department with the specified location code
     *         if found, or empty if no match exists
     */
    Optional<Department> findByLocactionCode(String locationCode);
}