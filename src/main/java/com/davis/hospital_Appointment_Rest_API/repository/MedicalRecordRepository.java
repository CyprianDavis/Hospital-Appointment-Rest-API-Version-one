package com.davis.hospital_Appointment_Rest_API.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.davis.hospital_Appointment_Rest_API.model.MedicalRecord;

/**
 * Repository interface for {@link MedicalRecord} entities.
 * 
 * <p>Provides CRUD operations and custom queries for accessing and managing patient medical records,
 * including search capabilities by patient and doctor names.</p>
 * 
 * <p><b>Key Features:</b>
 * <ul>
 *   <li>Search medical records by patient name (surname, given name, or other name)</li>
 *   <li>Search medical records by doctor name (surname, given name, or other name)</li>
 *   <li>Case-insensitive partial matching for all name searches</li>
 * </ul>
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-03
 * @see MedicalRecord
 * @see JpaRepository
 */
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

    /**
     * Searches medical records by patient name fields with partial matching and case insensitivity.
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
     * @return list of matching medical records (empty if none found)
     */
    @Query("SELECT mr FROM MedicalRecord mr WHERE " +
           "LOWER(mr.patient.surName) LIKE LOWER(CONCAT('%', :nameTerm, '%')) OR " +
           "LOWER(mr.patient.givenName) LIKE LOWER(CONCAT('%', :nameTerm, '%')) OR " +
           "LOWER(mr.patient.otherName) LIKE LOWER(CONCAT('%', :nameTerm, '%'))")
    List<MedicalRecord> searchByPatientName(@Param("nameTerm") String nameTerm);
    
    /**
     * Searches medical records by doctor name fields with partial matching and case insensitivity.
     * 
     * <p>Search is performed across three doctor name fields:
     * <ul>
     *   <li>Surname (family name)</li>
     *   <li>Given name (first name)</li>
     *   <li>Other name (middle name)</li>
     * </ul>
     * </p>
     * 
     * @param searchTerm the search term to match against doctor names
     * @return list of matching medical records (empty if none found)
     */
    @Query("SELECT mr FROM MedicalRecord mr WHERE " +
           "LOWER(mr.doctor.surName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(mr.doctor.givenName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(mr.doctor.otherName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<MedicalRecord> searchByDoctor(@Param("searchTerm") String searchTerm);
}