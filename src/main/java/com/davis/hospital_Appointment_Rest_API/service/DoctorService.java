package com.davis.hospital_Appointment_Rest_API.service;

import java.util.List;

import com.davis.hospital_Appointment_Rest_API.model.Doctor;

/**
 * Service interface for handling business logic related to {@link Doctor} entities.
 * <p>
 * Provides operations for searching doctors by specialization and name components,
 * in addition to generic CRUD operations inherited from the {@code Service} interface.
 * </p>
 * 
 * @author CYPRIAN DAVIS
 */
public interface DoctorService extends Service<Doctor, String> {
    
    /**
     * Searches for doctors by their specialization.
     * 
     * <p>This method returns a list of doctors whose specialization
     * matches the provided specialization name.</p>
     * 
     * @param specialization the medical specialization to search for
     * @return a list of doctors with the specified specialization;
     *         an empty list if no doctors match
     */
    List<Doctor> searchBySpecialization(String specialization);
    
    /**
     * Searches for doctors by matching their name components.
     * 
     * <p>This method attempts to match the provided name string
     * against the doctor's surname, given name, or other names.</p>
     * 
     * @param names a string containing one or more name components
     * @return a list of doctors whose names match the given components;
     *         an empty list if no matches are found
     */
    List<Doctor> searchByNames(String names);
}
