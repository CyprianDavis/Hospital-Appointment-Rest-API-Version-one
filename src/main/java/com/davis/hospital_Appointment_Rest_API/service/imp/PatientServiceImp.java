package com.davis.hospital_Appointment_Rest_API.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.davis.hospital_Appointment_Rest_API.dto.PatientDto;
import com.davis.hospital_Appointment_Rest_API.model.Patient;
import com.davis.hospital_Appointment_Rest_API.repository.PatientRepository;
import com.davis.hospital_Appointment_Rest_API.service.PatientService;

/**
 * Implementation of {@link PatientService} providing business logic for patient management.
 *
 * <p>This service class handles all patient-related operations including CRUD functionality,
 * search capabilities, and data transfer object conversions. It serves as the bridge between
 * controllers and the persistence layer.</p>
 *
 * <p><b>Key Responsibilities:</b></p>
 * <ul>
 *   <li>Patient entity lifecycle management</li>
 *   <li>Name-based patient search operations</li>
 *   <li>Conversion between entities and DTOs</li>
 *   <li>Transaction management</li>
 *   <li>Business rule enforcement</li>
 * </ul>
 *
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-03
 */
@Service
@Transactional(readOnly = true)
public class PatientServiceImp implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    /**
     * Retrieves a patient by their unique identifier.
     *
     * @param id the unique identifier of the patient
     * @return an {@link Optional} containing the found patient, or empty if not found
     * @throws IllegalArgumentException if {@code id} is {@code null} or empty
     */
    public Optional<Patient> findById(String id) {
        return patientRepository.findById(id);
    }

    /**
     * Retrieves all patients stored in the system.
     *
     * @return a list of all {@link Patient} entities, or an empty list if none exist
     */
    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    /**
     * Saves the provided {@link Patient} entity to the database.
     *
     * @param patient the patient entity to persist
     * @return the saved patient entity with generated values populated
     * @throws IllegalArgumentException if {@code patient} is {@code null}
     */
    @Override
    @Transactional
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    /**
     * Searches for patients by name using the deprecated method.
     *
     * @param name the name to search for
     * @return a list of patients matching the name
     * @deprecated Use {@link #searchPatientByName(String)} instead
     */
    @Override
    @Deprecated
    public List<Patient> searchByName(String name) {
        return patientRepository.searchByName(name);
    }

    /**
     * Searches for patients by name and returns a list of {@link PatientDto} objects.
     *
     * @param name the name to search for
     * @return a list of patient DTOs matching the name
     */
    @Override
    public List<PatientDto> searchPatientByName(String name) {
        return patientRepository.searchPatientByName(name);
    }

    /**
     * Retrieves all patients in the system as {@link PatientDto} objects.
     *
     * @return a list of patient DTOs
     */
    @Override
    public List<PatientDto> findAllPatients() {
        return patientRepository.findAllPatients();
    }

	@Override
	public List<PatientDto> findAllPatients(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

    
}
