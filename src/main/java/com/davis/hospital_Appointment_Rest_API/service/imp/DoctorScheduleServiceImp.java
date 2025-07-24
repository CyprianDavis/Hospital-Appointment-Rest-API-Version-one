package com.davis.hospital_Appointment_Rest_API.service.imp;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davis.hospital_Appointment_Rest_API.dto.ViewDoctorSchedule;
import com.davis.hospital_Appointment_Rest_API.model.DoctorSchedule;
import com.davis.hospital_Appointment_Rest_API.repository.DoctorScheduleRepository;
import com.davis.hospital_Appointment_Rest_API.service.DoctorScheduleService;

/**
 * Service implementation for doctor schedule management operations.
 * <p>
 * Provides business logic for managing doctor schedules including creation, retrieval,
 * and search functionality. Implements the {@link DoctorScheduleService} interface.
 *
 * <p><b>Key Responsibilities:</b>
 * <ul>
 *   <li>Managing CRUD operations for doctor schedules</li>
 *   <li>Calculating available appointment slots</li>
 *   <li>Searching schedules by various criteria</li>
 *   <li>Converting entities to DTOs for API responses</li>
 * </ul>
 * </p>
 *
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-07-06
 * @see DoctorScheduleService
 * @see DoctorScheduleRepository
 */
@Service
public class DoctorScheduleServiceImp implements DoctorScheduleService {

    @Autowired
    private DoctorScheduleRepository doctorScheduleRepository;

    /**
     * Retrieves all doctor schedules from the repository.
     *
     * @return List of all {@link DoctorSchedule} entities
     */
    @Override
    public List<DoctorSchedule> findAll() {
        return doctorScheduleRepository.findAll();
    }

    /**
     * Persists a doctor schedule to the database.
     * <p>
     * Performs the following operations before saving:
     * <ul>
     *   <li>Validates the schedule is not null</li>
     *   <li>Calculates available appointment slots</li>
     *   <li>Sets creation timestamp</li>
     * </ul>
     *
     * @param schedule The doctor schedule to persist
     * @return The persisted {@link DoctorSchedule} entity
     * @throws IllegalArgumentException if schedule is null
     */
    @Override
    public DoctorSchedule save(DoctorSchedule schedule) {
        if (schedule == null) {
            throw new IllegalArgumentException("Schedule cannot be null");
        }
        
        schedule.setAvailableSlots(calculateAvailableSlots(schedule.getStartTime(), schedule.getEndTime()));
        schedule.setCreatedOn(LocalDateTime.now());
        
        return doctorScheduleRepository.save(schedule);
    }

    /**
     * Retrieves schedules by doctor specialization.
     *
     * @param specialization The medical specialization to filter by (e.g., "Cardiology")
     * @return List of matching {@link ViewDoctorSchedule} DTOs
     * @throws IllegalArgumentException if specialization is null or empty
     */
    @Override
    public List<ViewDoctorSchedule> findByDoctorSpecialization(String specialization) {
        return doctorScheduleRepository.findDtoByDoctorSpecialization(specialization);
    }

    /**
     * Searches schedules by doctor name using case-insensitive matching.
     * <p>
     * The search matches against all name components (surname, given name) and
     * returns results sorted alphabetically by doctor name.
     *
     * @param name The name or partial name to search for
     * @return List of matching {@link ViewDoctorSchedule} DTOs, sorted by doctor name
     * @throws IllegalArgumentException if name is null or empty
     */
    @Override
    public List<ViewDoctorSchedule> searchByDoctorName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name parameter cannot be null or empty");
        }

        return doctorScheduleRepository.searchDtoByDoctorName(name)
                .stream()
                .filter(schedule -> schedule.getDoctorName() != null && !schedule.getDoctorName().isEmpty())
                .sorted(Comparator.comparing(ViewDoctorSchedule::getDoctorName))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves schedules by day of week.
     *
     * @param day The day of week to filter by (e.g., "Monday")
     * @return List of matching {@link ViewDoctorSchedule} DTOs
     * @throws IllegalArgumentException if day is null or empty
     */
    @Override
    public List<ViewDoctorSchedule> findByDayOfWeek(String day) {
        return doctorScheduleRepository.findDtoByDayOfWeek(day);
    }
    
    /**
     * Calculates available appointment slots between two times.
     * <p>
     * Each slot is assumed to be 20 minutes long. Deducts appropriate break times
     * based on schedule duration.
     *
     * @param startTime The schedule start time (required)
     * @param endTime The schedule end time (required)
     * @return Number of available 20-minute appointment slots
     * @throws IllegalArgumentException if:
     *         <ul>
     *           <li>Either time parameter is null</li>
     *           <li>Start time is after end time</li>
     *         </ul>
     */
    private int calculateAvailableSlots(LocalTime startTime, LocalTime endTime) {
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("Times cannot be null");
        }
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time cannot be after end time");
        }
        
        long totalMinutes = Duration.between(startTime, endTime).toMinutes();
        long breakMinutes = calculateBreakMinutes(totalMinutes);
        return (int) ((totalMinutes - breakMinutes) / 20);
    }

    /**
     * Determines break duration based on total schedule length.
     * <p>
     * Break policy:
     * <ul>
     *   <li>Less than 3 hours: No breaks</li>
     *   <li>3-6 hours: 15 minute break</li>
     *   <li>Over 6 hours: 45 minutes total breaks</li>
     * </ul>
     *
     * @param totalMinutes Total schedule duration in minutes
     * @return Total break time in minutes
     */
    private long calculateBreakMinutes(long totalMinutes) {
        if (totalMinutes > 360) { // >6 hours
            return 45;
        } else if (totalMinutes >= 180) { // 3-6 hours
            return 15;
        }
        return 0;
    }

    /**
     * Retrieves available doctor schedules matching the specified medical specialization and date.
     * <p>
     * This method performs the following operations:
     * <ol>
     *   
     *   <li>Converts the provided date to the corresponding day of week</li>
     *   <li>Queries for schedules matching both the specialization and day of week</li>
     *   <li>Filters results to only include confirmed schedules with available slots</li>
     *   <li>Excludes schedules for past dates</li>
     * </ol>
     * </p>
     *
     * @param specialization the medical specialization to search for (e.g., "Cardiology", "Pediatrics")
     *        Must not be {@code null} or empty
     * @param date the date to check availability for. Must be today or a future date
     *        Must not be {@code null}
     * @return a {@code List<DoctorSchedule>} containing matching schedules with available slots,
     *         ordered by earliest available time. Returns empty list if no matches found
     * @throws IllegalArgumentException if:
     *         <ul>
     *           <li>{@code specialization} is {@code null} or empty</li>
     *           <li>{@code date} is {@code null}</li>
     *           <li>{@code date} is in the past</li>
     *         </ul>
     * @see DoctorScheduleRepository#findBySpecializationAndDate(String, LocalDate)
     */
    @Override
    public List<DoctorSchedule> findBySpecializationAndDate(String specialization, LocalDate date) {
       
        
        return doctorScheduleRepository.findBySpecializationAndDate(specialization, date)
                .stream()
                .sorted(Comparator.comparing(DoctorSchedule::getStartTime))
                .collect(Collectors.toList());
    }
}