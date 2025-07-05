package com.davis.hospital_Appointment_Rest_API.service.imp;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davis.hospital_Appointment_Rest_API.dto.ViewDoctor;
import com.davis.hospital_Appointment_Rest_API.model.Doctor;
import com.davis.hospital_Appointment_Rest_API.repository.DoctorRepository;
import com.davis.hospital_Appointment_Rest_API.service.DoctorService;

/**
 * Service implementation for doctor-related operations in the Hospital Appointment System.
 * <p>
 * Provides concrete implementation of {@link DoctorService} interface, handling business logic
 * for doctor management and search operations. Returns {@link ViewDoctor} DTOs for read operations
 * while maintaining {@link Doctor} entities for create/update operations.
 * </p>
 *
 * <p><b>Key Responsibilities:</b>
 * <ul>
 *   <li>Managing doctor persistence operations</li>
 *   <li>Implementing search functionality</li>
 *   <li>Converting between entities and DTOs</li>
 *   <li>Enforcing business rules and validation</li>
 *   <li>Providing both entity and DTO views of doctor data</li>
 * </ul>
 * </p>
 *
 * @author CYPRIAN DAVIS
 * @version 2.0
 * @since 2025-06-29
 * @see DoctorService
 * @see ViewDoctor
 * @see DoctorRepository
 */
@Service
public class DoctorServiceImp implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    /**
     * Retrieves all doctors from the system as full entities.
     * <p>
     * This method returns complete {@link Doctor} entities with all relationships loaded.
     * For display purposes where only basic information is needed, consider using
     * {@link #findAllDoctorsAsViewDoctors()} instead for better performance.
     * </p>
     *
     * @return list of all {@link Doctor} entities in the system;
     *         empty list if no doctors exist (never null)
     */
    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    /**
     * Retrieves all doctors as lightweight {@link ViewDoctor} DTOs.
     * <p>
     * This optimized version returns only the fields needed for display purposes,
     * providing better performance than the entity-based {@link #findAll()} method.
     * The DTOs contain all essential doctor information without the overhead of
     * loading full entity relationships.
     * </p>
     *
     * @return list of all {@link ViewDoctor} DTOs in the system;
     *         empty list if no doctors exist (never null)
     */
    @Override
    public List<ViewDoctor> findAllDoctorsAsViewDoctors() {
        return doctorRepository.findAllDoctorsAsViewDoctors();
    }

    /**
     * Persists a doctor entity in the system.
     * <p>
     * Handles both creation of new doctors and updates to existing ones.
     * Performs basic validation before persistence.
     * </p>
     *
     * @param doctor the doctor entity to be persisted (must not be null)
     * @return the persisted {@link Doctor} entity with generated ID if new
     * @throws IllegalArgumentException if doctor parameter is null
     * @throws org.springframework.dao.DataAccessException on persistence failure
     */
    @Override
    public Doctor save(Doctor doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor entity cannot be null");
        }
        return doctorRepository.save(doctor);
    }

    /**
     * Finds doctors by medical specialization and returns them as {@link ViewDoctor} DTOs.
     * <p>
     * The search is case-sensitive and requires exact specialization name match.
     * Results contain only display-optimized DTOs with essential doctor information.
     * </p>
     *
     * @param specialization the medical specialization to search for
     *        (e.g., "Cardiology", "Pediatrics"; must not be null or empty)
     * @return list of {@link ViewDoctor} DTOs matching the specialization;
     *         empty list if no matches found (never null)
     * @throws IllegalArgumentException if specialization is null or empty
     */
    @Override
    public List<ViewDoctor> searchBySpecialization(String specialization) {
        if (specialization == null || specialization.trim().isEmpty()) {
            throw new IllegalArgumentException("Specialization cannot be null or empty");
        }
        return doctorRepository.findBySpecialization(specialization);
    }

    /**
     * Performs an advanced name search across all doctor name fields.
     * <p>
     * Searches case-insensitively across surname, given name, and other names.
     * Handles multiple search terms by returning only doctors matching all terms.
     * Results are returned as display-optimized {@link ViewDoctor} DTOs.
     * </p>
     *
     * @param names the search term(s) to match against doctor names
     *        (will be split on whitespace; must not be null or empty)
     * @return list of {@link ViewDoctor} DTOs matching all search terms;
     *         empty list if no matches found (never null)
     * @throws IllegalArgumentException if names parameter is null or empty
     */
    @Override
    public List<ViewDoctor> searchByNames(String names) {
        if (names == null || names.trim().isEmpty()) {
            throw new IllegalArgumentException("Names parameter cannot be null or empty");
        }

        String[] terms = names.trim().split("\\s+");
        
        // Get matches for each term in parallel
        List<List<ViewDoctor>> allMatches = Arrays.stream(terms)
            .map(doctorRepository::searchByName)
            .toList();
        
        // Find intersection of all result sets
        return allMatches.stream()
            .reduce((list1, list2) -> {
                Set<ViewDoctor> set = new HashSet<>(list2);
                return list1.stream()
                    .filter(set::contains)
                    .toList();
            })
            .orElse(Collections.emptyList());
    }

    /**
     * Retrieves a doctor by their unique identifier.
     * <p>
     * Returns the result as an {@link Optional} to explicitly handle the
     * case where no doctor exists with the given ID.
     * </p>
     *
     * @param id the unique identifier of the doctor to find
     *        (must not be null or empty)
     * @return {@link Optional} containing the found {@link Doctor} entity,
     *         or empty Optional if not found
     */
    public Optional<Doctor> findById(String id) {
       
        return doctorRepository.findById(id);
    }
}