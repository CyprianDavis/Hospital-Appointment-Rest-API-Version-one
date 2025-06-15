package com.davis.hospital_Appointment_Rest_API.service;

import java.awt.print.Pageable;
import java.util.List;
import org.springframework.data.domain.Page;
import com.davis.hospital_Appointment_Rest_API.model.DoctorSchedule;

/**
 * Service interface for managing {@link DoctorSchedule} entities.
 * Extends the generic {@link Service} interface with doctor schedule-specific operations.
 * 
 * <p>Provides methods for searching and managing doctor schedules by various criteria
 * including specialization, name, and day of week.</p>
 *
 * @author CYPRIAN DAVIS
 * @since 1.0
 * @see Service
 * @see DoctorSchedule
 */
public interface DoctorScheduleService extends Service<DoctorSchedule> {

    /**
     * Finds doctor schedules by their medical specialization.
     *
     * @param specialization the medical specialization to search for (case-insensitive)
     * @return a list of doctor schedules matching the specialization,
     *         ordered by doctor's last name. Returns empty list if no matches found
     * @throws IllegalArgumentException if specialization is null or empty
     */
    List<DoctorSchedule> findByDoctorSpecialization(String specialization);

    /**
     * Performs a comprehensive search of doctor schedules with pagination support.
     *
     * @param pageable pagination information including page number, size, and sorting
     * @return a page of doctor schedule results matching the search criteria
     * @throws IllegalArgumentException if pageable is null
     */
    Page<DoctorSchedule> comprehensiveSearchPaginated(Pageable pageable);

    /**
     * Searches for doctor schedules by the doctor's name.
     *
     * @param name the doctor's name to search for (case-insensitive partial match)
     * @return a list of matching doctor schedules ordered by appointment availability date,
     *         or empty list if no matches found
     * @throws IllegalArgumentException if name is null or empty
     */
    List<DoctorSchedule> searchByDoctorName(String name);

    /**
     * Finds doctor schedules available on a specific day of week.
     *
     * @param day the day of week to search for (case-insensitive, full day name or abbreviation)
     * @return a list of doctor schedules available on the specified day,
     *         ordered by start time. Returns empty list if no matches found
     * @throws IllegalArgumentException if day is null, empty, or not a valid day name
     */
    List<DoctorSchedule> findByDayOfWeek(String day);
}