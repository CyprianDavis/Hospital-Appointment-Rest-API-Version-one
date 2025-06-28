package com.davis.hospital_Appointment_Rest_API.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davis.hospital_Appointment_Rest_API.model.Doctor;
import com.davis.hospital_Appointment_Rest_API.repository.DoctorRepository;
import com.davis.hospital_Appointment_Rest_API.service.DoctorService;

/**
 * Implementation of the {@link DoctorService} interface that provides
 * CRUD operations and search functionality for {@link Doctor} entities.
 * <p>
 * This service handles all business logic related to doctor management,
 * delegating persistence operations to the {@link DoctorRepository}.
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-29
 * @see DoctorService
 * @see Doctor
 * @see DoctorRepository
 */
@Service
public class DoctorServiceImp implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    /**
     * Retrieves all doctors from the database.
     *
     * @return a list of all doctors (empty list if no doctors found)
     */
    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    /**
     * Saves a doctor entity to the database.
     * <p>
     * Can be used for both creating new doctors and updating existing ones.
     * </p>
     *
     * @param doctor the doctor entity to be saved (must not be null)
     * @return the saved doctor entity
     * @throws IllegalArgumentException if the doctor parameter is null
     */
    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    /**
     * Searches for doctors by their specialization.
     *
     * @param specialization the specialization to search for
     * @return a list of doctors matching the specialization (empty list if none found)
     * @throws IllegalArgumentException if the specialization parameter is null
     */
    @Override
    public List<Doctor> searchBySpecialization(String specialization) {
        return doctorRepository.searchByName(specialization);
    }

    /**
     * Searches for doctors by their names.
     *
     * @param names the name or partial name to search for
     * @return a list of doctors matching the name criteria (empty list if none found)
     * @throws IllegalArgumentException if the names parameter is null
     */
    @Override
    public List<Doctor> searchByNames(String names) {
        return doctorRepository.searchByName(names);
    }

    /**
     * Finds a doctor by their unique identifier.
     *
     * @param id the ID of the doctor to search for (must not be null or empty)
     * @return an {@link Optional} containing the doctor if found,
     *         or empty Optional if no doctor with the given ID exists
     * @throws IllegalArgumentException if the id parameter is null or empty
     */
    public Optional<Doctor> findById(String id) {
        return doctorRepository.findById(id);
    }
}