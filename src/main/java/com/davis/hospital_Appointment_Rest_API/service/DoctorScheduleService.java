package com.davis.hospital_Appointment_Rest_API.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.davis.hospital_Appointment_Rest_API.dto.ViewDoctorSchedule;
import com.davis.hospital_Appointment_Rest_API.model.DoctorSchedule;

/**
 * Service interface for managing {@link DoctorSchedule} entities.
 * Extends the generic {@link Service} interface with doctor schedule-specific operations.
 * 
 * <p>Provides methods for searching and managing doctor schedules by various criteria
 * including specialization, name, day of week, and date.</p>
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
    List<ViewDoctorSchedule> findByDoctorSpecialization(String specialization);

    /**
     * Searches for doctor schedules by the doctor's name.
     *
     * @param name the doctor's name to search for (case-insensitive partial match)
     * @return a list of matching doctor schedules ordered by appointment availability date,
     *         or empty list if no matches found
     * @throws IllegalArgumentException if name is null or empty
     */
    List<ViewDoctorSchedule> searchByDoctorName(String name);

    /**
     * Finds doctor schedules available on a specific day of week.
     *
     * @param day the day of week to search for (case-insensitive, full day name or abbreviation)
     * @return a list of doctor schedules available on the specified day,
     *         ordered by start time. Returns empty list if no matches found
     * @throws IllegalArgumentException if day is null, empty, or not a valid day name
     */
    List<ViewDoctorSchedule> findByDayOfWeek(String day);

    /**
     * Finds available doctor schedules by medical specialization and specific date.
     * <p>
     * Returns schedules that match both the specified medical specialization and are available
     * on the given date (converted to day of week). Only returns schedules with available slots
     * and confirmed availability status.
     * </p>
     *
     * @param specialization the medical specialization to filter by (e.g., "Cardiology")
     * @param date the date to check availability for (must be current or future date)
     * @return list of matching doctor schedule entities with available slots,
     *         ordered by earliest available time. Returns empty list if no matches found
     * @throws IllegalArgumentException if:
     *         <ul>
     *           <li>specialization is null or empty</li>
     *           <li>date is null</li>
     *           <li>date is in the past</li>
     *         </ul>
     */
    Optional<DoctorSchedule> findBySpecializationAndDate(String specialization, LocalDate date);
}