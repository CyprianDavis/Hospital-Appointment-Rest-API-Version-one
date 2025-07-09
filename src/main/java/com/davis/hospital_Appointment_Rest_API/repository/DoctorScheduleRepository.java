package com.davis.hospital_Appointment_Rest_API.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.davis.hospital_Appointment_Rest_API.dto.ViewDoctorSchedule;
import com.davis.hospital_Appointment_Rest_API.model.DoctorSchedule;

/**
 * Repository interface for managing doctor schedule information with DTO projections.
 * 
 * <p>Provides CRUD operations and custom queries that return {@link ViewDoctorSchedule} DTOs
 * instead of entities, following best practices for API layer separation.</p>
 * 
 * <p><b>Key Changes:</b>
 * <ul>
 *   <li>All query methods now return ViewDoctorSchedule DTOs</li>
 *   <li>Added constructor expressions in JPQL for DTO projection</li>
 *   <li>Maintained all existing search capabilities</li>
 *   <li>Added doctor name concatenation in queries</li>
 * </ul>
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 2.0
 * @since 2025-07-09
 * @see ViewDoctorSchedule
 */
public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, Long> {
    
    /**
     * Finds schedules by doctor's specialization and returns DTO projections
     * @param specialization the medical specialization to search for
     * @return list of schedule DTOs matching the specialization
     */
    @Query("SELECT NEW com.davis.hospital_Appointment_Rest_API.dto.ViewDoctorSchedule(" +
           "CONCAT(d.doctor.surName, ' ', d.doctor.givenName), " +
           "d.doctor.specialization,"+
           "d.dayOfWeek, " +
           "d.startTime, " +
           "d.endTime, " +
           "d.availableSlots, " +
           "d.isConfirmed) " +
           "FROM DoctorSchedule d " +
           "WHERE d.doctor.specialization = :specialization")
    List<ViewDoctorSchedule> findDtoByDoctorSpecialization(@Param("specialization") String specialization);
   
    /**
     * Searches schedules by doctor name fields and returns DTO projections
     * 
     * @param nameTerm term to match against doctor names
     * @return list of schedule DTOs matching the name criteria
     */
    @Query("SELECT NEW com.davis.hospital_Appointment_Rest_API.dto.ViewDoctorSchedule(" +
           "CONCAT(d.doctor.surName, ' ', d.doctor.givenName), " +
           "d.doctor.specialization,"+
           "d.dayOfWeek, " +
           "d.startTime, " +
           "d.endTime, " +
           "d.availableSlots, " +
           "d.isConfirmed) " +
           "FROM DoctorSchedule d WHERE " +
           "LOWER(d.doctor.surName) LIKE LOWER(CONCAT('%', :nameTerm, '%')) OR " +
           "LOWER(d.doctor.givenName) LIKE LOWER(CONCAT('%', :nameTerm, '%'))")
    List<ViewDoctorSchedule> searchDtoByDoctorName(@Param("nameTerm") String nameTerm);
    
    /**
     * Finds schedules by day of week and returns DTO projections
     * 
     * @param dayOfWeek the day name to search for (e.g., "Monday")
     * @return list of schedule DTOs for the specified day
     */
    @Query("SELECT NEW com.davis.hospital_Appointment_Rest_API.dto.ViewDoctorSchedule(" +
           "CONCAT(d.doctor.surName, ' ', d.doctor.givenName), " +
           "d.doctor.specialization,"+
           "d.dayOfWeek, " +
           "d.startTime, " +
           "d.endTime, " +
           "d.availableSlots, " +
           "d.isConfirmed) " +
           "FROM DoctorSchedule d " +
           "WHERE d.dayOfWeek = :dayOfWeek")
    List<ViewDoctorSchedule> findDtoByDayOfWeek(@Param("dayOfWeek") String dayOfWeek);
}