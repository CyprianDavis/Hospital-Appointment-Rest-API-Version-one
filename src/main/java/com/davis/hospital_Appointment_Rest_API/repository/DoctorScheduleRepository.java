package com.davis.hospital_Appointment_Rest_API.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.davis.hospital_Appointment_Rest_API.model.DoctorSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Repository interface for managing {@link DoctorSchedule} entities.
 * 
 * <p>Provides CRUD operations and custom queries for accessing and managing doctor schedule information,
 * including search capabilities by doctor attributes, schedule details, and paginated results.</p>
 * 
 * <p><b>Key Features:</b>
 * <ul>
 *   <li>Search schedules by doctor specialization</li>
 *   <li>Comprehensive text search across doctor names and attributes</li>
 *   <li>Paginated search results for large datasets</li>
 *   <li>Find schedules by day of week</li>
 *   <li>Retrieve all schedules for a specific doctor</li>
 * </ul>
 * </p>
 * 
 * <p><b>Usage Examples:</b>
 * <pre>
 * // Find schedules by specialization
 * List<DoctorSchedule> cardioSchedules = repository.findByDoctorSpecialization("Cardiology");
 * 
 * // Search doctor names
 * List<DoctorSchedule> smithSchedules = repository.searchByDoctorName("Smith");
 * 
 * // Get paginated results
 * Page<DoctorSchedule> page = repository.comprehensiveSearchPaginated(
 *     "John", 
 *     PageRequest.of(0, 10)
 * );
 * </pre>
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-03
 * @see DoctorSchedule
 * @see JpaRepository
 */
public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, Long> {
    
    /**
     * Finds schedules by doctor's specialization
     * @param specialization the medical specialization
     * @return list of matching schedules
     */
    @Query("SELECT ds FROM DoctorSchedule ds WHERE ds.doctor.specialization = :specialization")
    List<DoctorSchedule> findByDoctorSpecialization(@Param("specialization") String specialization);
   
    /**
     * Paginated version of comprehensive search
     * 
     * @param searchTerm the term to search across multiple fields
     * @param pageable pagination information
     * @return page of matching schedules
     */
    @Query("SELECT ds FROM DoctorSchedule ds WHERE " +
           "LOWER(ds.doctor.surName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(ds.doctor.givenName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(ds.doctor.otherName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(ds.doctor.specialization) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(ds.doctor.department.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<DoctorSchedule> comprehensiveSearchPaginated(
        @Param("searchTerm") String searchTerm, 
        Pageable pageable);
        
    /**
     * Finds doctor schedules by searching across all doctor name fields (surname, given name, other name)
     * with partial matching and case insensitivity.
     * 
     * @param nameTerm the search term to match against doctor names
     * @return list of matching schedules (empty if none found)
     */
    @Query("SELECT ds FROM DoctorSchedule ds WHERE " +
           "LOWER(ds.doctor.surName) LIKE LOWER(CONCAT('%', :nameTerm, '%')) OR " +
           "LOWER(ds.doctor.givenName) LIKE LOWER(CONCAT('%', :nameTerm, '%')) OR " +
           "LOWER(ds.doctor.userId) LIKE LOWER(CONCAT('%', :nameTerm, '%'))")
    List<DoctorSchedule> searchByDoctorName(@Param("nameTerm") String nameTerm);
    
    /**
     * Finds schedules by day of week
     * @param dayOfWeek the day name (e.g., "Monday")
     * @return list of matching schedules
     */
    List<DoctorSchedule> findByDayOfWeek(String dayOfWeek);
    
    /**
     * Finds all schedules for a specific doctor by doctor ID
     * @param doctorId the doctor's ID
     * @return list of matching schedules (empty if none found)
     */
    List<DoctorSchedule> findByDoctorId(Long doctorId);
}