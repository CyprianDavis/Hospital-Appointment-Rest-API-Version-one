package com.davis.hospital_Appointment_Rest_API.service;

import java.util.List;

import com.davis.hospital_Appointment_Rest_API.model.Prescription;

/**
 * Service interface for managing {@link Prescription} entities.
 * Extends the generic {@link Service} interface with prescription-specific operations.
 * Provides methods to search prescriptions by patient or doctor.
 *
 * @author CYPRIAN DAVIS
 * @since 1.0
 * @see Service
 * @see Prescription
 */
public interface PrescriptionService extends Service<Prescription> {

    /**
     * Searches for prescriptions associated with a specific patient.
     *
     * @param name the name of the patient to search for (case-sensitive)
     * @return a list of prescriptions for the specified patient, 
     *         or empty list if no matches found (never null)
     * @throws IllegalArgumentException if the name parameter is null or empty
     */
    List<Prescription> searchByPatient(String name);

    /**
     * Searches for prescriptions associated with a specific doctor.
     *
     * @param name the name of the doctor to search for (case-sensitive)
     * @return a list of prescriptions written by the specified doctor,
     *         or empty list if no matches found (never null)
     * @throws IllegalArgumentException if the name parameter is null or empty
     */
    List<Prescription> searchByDoctor(String name);
}