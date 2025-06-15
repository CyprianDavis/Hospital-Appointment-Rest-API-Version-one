package com.davis.hospital_Appointment_Rest_API.service;

import java.util.List;
import com.davis.hospital_Appointment_Rest_API.model.MedicalRecord;

/**
 * Service interface for managing {@link MedicalRecord} entities.
 * Extends the generic {@link Service} interface with medical record-specific operations.
 * 
 * <p>Provides methods for managing and searching medical records by various criteria
 * including doctor and patient name.</p>
 *
 * @author CYPRIAN DAVIS
 * @since 1.0
 * @see Service
 * @see MedicalRecord
 */
public interface MedicalRecordService extends Service<MedicalRecord> {

    /**
     * Searches for medical records associated with a specific doctor.
     *
     * @param doctor the name of the doctor to search for (case-insensitive)
     * @return a list of medical records created by the specified doctor,
     *         ordered by record date (newest first). Returns an empty list
     *         if no matches are found (never null)
     * @throws IllegalArgumentException if doctor parameter is null or empty
     */
    List<MedicalRecord> searchByDoctor(String doctor);

    /**
     * Searches for medical records associated with a specific patient.
     *
     * @param patient the name of the patient to search for (case-insensitive)
     * @return a list of medical records belonging to the specified patient,
     *         ordered by record date (newest first). Returns an empty list
     *         if no matches are found (never null)
     * @throws IllegalArgumentException if patient parameter is null or empty
     */
    List<MedicalRecord> searchByPatientName(String patient);
}