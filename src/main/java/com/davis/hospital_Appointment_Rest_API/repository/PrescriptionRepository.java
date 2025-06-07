package com.davis.hospital_Appointment_Rest_API.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.davis.hospital_Appointment_Rest_API.model.Prescription;

/**
 * Repository interface for {@link Prescription} entities.
 * 
 * <p>Provides CRUD operations and custom queries for managing prescription records,
 * including search capabilities by patient and doctor information.</p>
 * 
 * <p><b>Key Features:</b>
 * <ul>
 *   <li>Search prescriptions by patient name (surname, given name, or other name)</li>
 *   <li>Search prescriptions by doctor name or ID</li>
 *   <li>Case-insensitive partial matching for all searches</li>
 *   <li>Built-in JPA repository operations</li>
 * </ul>
 * </p>
 * 
 * <p><b>Usage Examples:</b>
 * <pre>
 * // Search prescriptions by patient name
 * List<Prescription> patientPrescriptions = prescriptionRepository.searchByPatientName("Smith");
 * 
 * // Search prescriptions by doctor information
 * List<Prescription> doctorPrescriptions = prescriptionRepository.searchByDoctor("Johnson");
 * </pre>
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-03
 * @see Prescription
 * @see JpaRepository
 */
@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    /**
     * Searches prescriptions by patient name fields with partial matching and case insensitivity.
     * 
     * <p>Search is performed across three patient name fields:
     * <ul>
     *   <li>Surname (family name)</li>
     *   <li>Given name (first name)</li>
     *   <li>Other name (middle name)</li>
     * </ul>
     * </p>
     * 
     * @param nameTerm the search term to match against patient names
     * @return list of matching prescriptions (empty if none found)
     */
    @Query("SELECT pr FROM Prescription pr WHERE " +
           "LOWER(pr.patient.surName) LIKE LOWER(CONCAT('%', :nameTerm, '%')) OR " +
           "LOWER(pr.patient.givenName) LIKE LOWER(CONCAT('%', :nameTerm, '%')) OR " +
           "LOWER(pr.patient.otherName) LIKE LOWER(CONCAT('%', :nameTerm, '%'))")
    List<Prescription> searchByPatientName(@Param("nameTerm") String nameTerm);

    /**
     * Searches prescriptions by doctor information with partial matching and case insensitivity.
     * 
     * <p>Search is performed across:
     * <ul>
     *   <li>Doctor surname</li>
     *   <li>Doctor given name</li>
     *   <li>Doctor other name</li>
     *   <li>Doctor user ID</li>
     * </ul>
     * </p>
     * 
     * @param nameTerm the search term to match against doctor information
     * @return list of matching prescriptions (empty if none found)
     */
    @Query("SELECT pr FROM Prescription pr WHERE " +
           "LOWER(pr.doctor.surName) LIKE LOWER(CONCAT('%', :nameTerm, '%')) OR " +
           "LOWER(pr.doctor.givenName) LIKE LOWER(CONCAT('%', :nameTerm, '%')) OR " +
           "LOWER(pr.doctor.otherName) LIKE LOWER(CONCAT('%', :nameTerm, '%')) OR " +
           "LOWER(pr.doctor.userId) LIKE LOWER(CONCAT('%', :nameTerm, '%'))")
    List<Prescription> searchByDoctor(@Param("nameTerm") String nameTerm);
}