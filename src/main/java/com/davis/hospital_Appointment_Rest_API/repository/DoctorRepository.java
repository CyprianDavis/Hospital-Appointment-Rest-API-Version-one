package com.davis.hospital_Appointment_Rest_API.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.davis.hospital_Appointment_Rest_API.model.Doctor;

/**
 * Repository interface for {@link Doctor} entities.
 * 
 * <p>Provides CRUD operations and custom queries for managing doctor information,
 * including search capabilities by specialization and name fields.</p>
 * 
 * <p><b>Key Features:</b>
 * <ul>
 *   <li>Find doctors by medical specialization</li>
 *   <li>Search doctors by name fields (surname, given name, or other name)</li>
 *   <li>Inherits all standard JPA repository operations</li>
 * </ul>
 * </p>
 * 
 * <p><b>Usage Examples:</b>
 * <pre>
 * // Find all cardiologists
 * List<Doctor> cardiologists = doctorRepository.findBySpecialization("Cardiology");
 * 
 * // Search doctors by name components
 * List<Doctor> nameMatches = doctorRepository.findBySurNameOrGivenNameOrOtherName(
 *     "Smith", "John", null);
 * </pre>
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-03
 * @see Doctor
 * @see JpaRepository
 */
public interface DoctorRepository extends JpaRepository<Doctor, String> {

    /**
     * Finds all doctors with the specified medical specialization.
     * 
     * @param specialization the medical specialization to search for 
     *        (e.g., "Cardiology", "Pediatrics")
     * @return list of doctors with matching specialization (empty if none found)
     */
    List<Doctor> findBySpecialization(String specialization);
    
   

    /**
     * Searches for doctors by matching any of the provided name components
     * against the surname, given name, or other name fields.
     * 
     * @param nameComponents the collection of name components to match
     * @return list of doctors matching any of the name components
     */
    @Query("SELECT d FROM Doctor d WHERE " +
    		"LOWER(d.surName) IN :names OR " +
    		"LOWER(d.givenName) IN :names OR " +
    		"LOWER(d.otherName) IN :names")
       List<Doctor> searchByName(@Param("names") Collection<String> nameComponents);
}
