package com.davis.hospital_Appointment_Rest_API.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.davis.hospital_Appointment_Rest_API.model.Appointment;

/**
 * Repository interface for managing {@link Appointment} entities in the database.
 * Provides custom query methods for appointment-related operations.
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-03
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,String> {
    
    /**
     * Searches for appointments by patient name (case-insensitive).
     * Matches against surname, given name, or other name fields.
     *
     * @param name The name or partial name to search for
     * @return List of appointments matching the search criteria
     */
    @Query("SELECT a FROM Appointment a WHERE "
            +  "LOWER(a.patient.surName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
               "LOWER(a.patient.givenName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
               "LOWER(a.patient.otherName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Appointment> searchByPatientName(@Param("name")String name);
    
    /**
     * Searches for appointments by doctor name (case-insensitive).
     * Matches against surname, given name, or other name fields.
     *
     * @param name The name or partial name to search for
     * @return List of appointments matching the search criteria
     */
    @Query("SELECT a FROM Appointment a WHERE "
            +  "LOWER(a.doctor.surName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
               "LOWER(a.doctor.givenName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
               "LOWER(a.doctor.otherName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Appointment> searchByDoctorName(@Param("name")String name);

    /**
     * Finds the most recent active appointment for a specific doctor.
     * An appointment is considered active if its status is neither "Completed" nor "Cancelled".
     * Results are ordered by date and start time in descending order to get the most recent appointment.
     *
     * @param doctorId The ID of the doctor to search appointments for
     * @return An {@link Optional} containing the appointment if found, empty otherwise
     */
    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId " +
           "AND a.status NOT IN ('Completed', 'Cancelled') " +
           "ORDER BY a.date DESC, a.startTime DESC LIMIT 1")
    Optional<Appointment> findLastActiveAppointmentByDoctorId(@Param("doctorId") String doctorId);
}