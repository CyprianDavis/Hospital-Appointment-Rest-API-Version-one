package com.davis.hospital_Appointment_Rest_API.service;

import java.util.List;
import com.davis.hospital_Appointment_Rest_API.model.Appointment;

/**
 * Service interface for managing {@link Appointment} entities.
 * Extends the generic {@link Service} interface with appointment-specific operations.
 * 
 * <p>Provides methods for managing and searching patient appointments by various criteria
 * including patient name and doctor name.</p>
 *
 * @author CYPRIAN DAVIS
 * @since 1.0
 * @see Service
 * @see Appointment
 */
public interface AppointmentService extends Service<Appointment> {

    /**
     * Searches for appointments associated with a specific patient.
     *
     * @param name the patient's name to search for (case-insensitive)
     * @return a list of appointments for the specified patient,
     *         ordered by appointment date (newest first).
     *         Returns an empty list if no matches are found (never null)
     * @throws IllegalArgumentException if the name parameter is null or empty
     */
    List<Appointment> searchByPatientName(String name);

    /**
     * Searches for appointments associated with a specific doctor.
     *
     * @param name the doctor's name to search for (case-insensitive)
     * @return a list of appointments with the specified doctor,
     *         ordered by appointment date (newest first).
     *         Returns an empty list if no matches are found (never null)
     * @throws IllegalArgumentException if the name parameter is null or empty
     */
    List<Appointment> searchByDoctorName(String name);
}