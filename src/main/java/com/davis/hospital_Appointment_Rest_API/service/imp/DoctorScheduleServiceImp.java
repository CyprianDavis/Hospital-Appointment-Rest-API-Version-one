package com.davis.hospital_Appointment_Rest_API.service.imp;

import java.awt.print.Pageable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.davis.hospital_Appointment_Rest_API.model.DoctorSchedule;
import com.davis.hospital_Appointment_Rest_API.repository.DoctorScheduleRepository;
import com.davis.hospital_Appointment_Rest_API.service.DoctorScheduleService;

/**
 * Implementation of the {@link DoctorScheduleService} interface providing
 * business logic for doctor schedule management.
 * <p>
 * This service handles operations related to doctor schedules including:
 * <ul>
 *   <li>CRUD operations for doctor schedules</li>
 *   <li>Search functionality by various criteria</li>
 *   <li>Calculation of available appointment slots</li>
 * </ul>
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-07-06
 * @see DoctorScheduleService
 * @see DoctorScheduleRepository
 * @see DoctorSchedule
 */
@Service
public class DoctorScheduleServiceImp implements DoctorScheduleService {

    @Autowired
    private DoctorScheduleRepository doctorScheduleRepository;

    /**
     * Retrieves all doctor schedules from the database.
     *
     * @return List of all doctor schedules
     */
    @Override
    public List<DoctorSchedule> findAll() {
        return doctorScheduleRepository.findAll();
    }

    /**
     * Saves a doctor schedule to the database.
     * <p>
     * Automatically calculates available slots and sets creation timestamp
     * before saving.
     * </p>
     *
     * @param schedule The doctor schedule to save
     * @return The saved doctor schedule
     * @throws IllegalArgumentException if schedule is null
     */
    @Override
    public DoctorSchedule save(DoctorSchedule schedule) {
        if (schedule == null) {
            throw new IllegalArgumentException("Schedule cannot be null");
        }
        
        schedule.setAvailableSlots(calculateAvailableSlots(schedule.getStartTime(), schedule.getEndTime()));
        LocalDateTime now = LocalDateTime.now();
        schedule.setCreatedOn(now);
        
        return doctorScheduleRepository.save(schedule);
    }

    /**
     * Finds schedules by doctor specialization.
     *
     * @param specialization The medical specialization to search for
     * @return List of matching doctor schedules
     * @throws IllegalArgumentException if specialization is null or empty
     */
    @Override
    public List<DoctorSchedule> findByDoctorSpecialization(String specialization) {
        if (specialization == null || specialization.trim().isEmpty()) {
            throw new IllegalArgumentException("Specialization cannot be null or empty");
        }
        return doctorScheduleRepository.findByDoctorSpecialization(specialization);
    }

    /**
     * Performs a paginated comprehensive search of doctor schedules.
     *
     * @param pageable Pagination information
     * @return Page of doctor schedules
     */
    @Override
    public Page<DoctorSchedule> comprehensiveSearchPaginated(Pageable pageable) {
        return null; // Implementation pending
    }

    /**
     * Searches for doctor schedules by doctor name.
     * <p>
     * Supports multi-term search by finding intersection of all term matches.
     * </p>
     *
     * @param name The name or name parts to search for
     * @return List of matching doctor schedules
     * @throws IllegalArgumentException if name is null or empty
     */
    @Override
    public List<DoctorSchedule> searchByDoctorName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name parameter cannot be null or empty");
        }
        
        String[] terms = name.trim().split("\\s+");
        
        List<List<DoctorSchedule>> allMatches = Arrays.stream(terms)
                .map(doctorScheduleRepository::searchByDoctorName)
                .toList();
                
        return allMatches.stream()
                .reduce((list1, list2) -> {
                    Set<DoctorSchedule> set = new HashSet<>(list2);
                    return list1.stream()
                            .filter(set::contains)
                            .toList();
                })
                .orElse(Collections.emptyList());
    }

    /**
     * Finds schedules by day of week.
     *
     * @param day The day of week to search for (e.g., "Monday")
     * @return List of matching doctor schedules
     * @throws IllegalArgumentException if day is null or empty
     */
    @Override
    public List<DoctorSchedule> findByDayOfWeek(String day) {
        if (day == null || day.trim().isEmpty()) {
            throw new IllegalArgumentException("Day parameter cannot be null or empty");
        }
        return doctorScheduleRepository.findByDayOfWeek(day);
    }
    
    /**
     * Calculates the number of available 20-minute appointment slots
     * between start and end times, accounting for appropriate breaks.
     *
     * @param startTime The schedule start time
     * @param endTime The schedule end time
     * @return Number of available appointment slots
     * @throws IllegalArgumentException if times are null or invalid
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
        long availableMinutes = totalMinutes - breakMinutes;
        
        return (int) (availableMinutes / 20);
    }

    /**
     * Calculates total break time based on schedule duration.
     * <p>
     * Break logic:
     * <ul>
     *   <li>No breaks for schedules under 3 hours</li>
     *   <li>15-minute break for schedules 3-6 hours</li>
     *   <li>45 minutes total breaks (30 lunch + 15 breakfast) for schedules over 6 hours</li>
     * </ul>
     * </p>
     *
     * @param totalMinutes Total schedule duration in minutes
     * @return Total break time in minutes
     */
    private long calculateBreakMinutes(long totalMinutes) {
        long breakMinutes = 0;
        
        if (totalMinutes > 360) { // >6 hours
            breakMinutes += 30; // Lunch break
            breakMinutes += 15; // Breakfast break
        }
        else if (totalMinutes >= 180) { // 3-6 hours
            breakMinutes += 15; // Single break
        }
        
        return breakMinutes;
    }
}