package com.davis.hospital_Appointment_Rest_API.service;

import java.util.List;
import com.davis.hospital_Appointment_Rest_API.model.Billing;

/**
 * Service interface for managing {@link Billing} entities.
 * Extends the generic {@link Service} interface with billing-specific operations.
 * 
 * <p>Provides methods for managing and searching patient billing records
 * including searching by patient name.</p>
 *
 * @author CYPRIAN DAVIS
 * @since 1.0
 * @see Service
 * @see Billing
 */
public interface BillingService extends Service<Billing> {

    /**
     * Searches for billing records associated with a specific patient.
     *
     * @param name the patient's name to search for (case-insensitive partial match)
     * @return a list of billing records for the specified patient,
     *         ordered by billing date descending (newest first).
     *         Returns an empty list if no matches are found (never null)
     * @throws IllegalArgumentException if the name parameter is null or empty
     */
    List<Billing> searchByPatientName(String name);
}