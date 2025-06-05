package com.davis.hospital_Appointment_Rest_API.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * Represents a medical record in the hospital appointment system.
 * A medical record contains patient health information including diagnosis,
 * test results, and treatment details, and is associated with a specific
 * patient, doctor, appointment, and prescription.
 * @author CYPRIAN DAVIS
 */
@Entity
public class MedicalRecord {
    /**
     * Unique identifier for the medical record
     */
    @Id
    private String id;
    
    /**
     * The patient associated with this medical record
     */
    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;
    
    /**
     * The doctor who created or is responsible for this medical record
     */
    @ManyToOne
    @JoinColumn(name = "doctorId")
    private Doctor doctor;
    
    /**
     * The appointment associated with this medical record, if applicable
     */
    @ManyToOne
    @JoinColumn(name = "appointment")
    private Appointment appointment;
    
    /**
     * The prescription associated with this medical record, if applicable
     */
    @ManyToOne
    @JoinColumn(name = "prescription")
    private Prescription prescription;
    
    /**
     * The diagnosis or medical condition documented in this record
     */
    private String diagnosis;
    
    /**
     * Results of any medical tests or lab work
     */
    private String testResults;
    
    /**
     * Additional notes or observations by the healthcare provider
     */
    private String note;
    
    /**
     * The date and time when this medical record was created
     */
    @Temporal(TemporalType.TIME)
    private Date recordDate;

	/**
	 * @param patient
	 * @param doctor
	 * @param appointment
	 * @param prescription
	 * @param diagnosis
	 * @param testResults
	 * @param note
	 * @param recordDate
	 */
	public MedicalRecord(Patient patient, Doctor doctor, Appointment appointment, Prescription prescription,
			String diagnosis, String testResults, String note, Date recordDate) {
		this.patient = patient;
		this.doctor = doctor;
		this.appointment = appointment;
		this.prescription = prescription;
		this.diagnosis = diagnosis;
		this.testResults = testResults;
		this.note = note;
		this.recordDate = recordDate;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}
	/**
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	/**
	 * @return the doctor
	 */
	public Doctor getDoctor() {
		return doctor;
	}
	/**
	 * @param doctor the doctor to set
	 */
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	/**
	 * @return the appointment
	 */
	public Appointment getAppointment() {
		return appointment;
	}
	/**
	 * @param appointment the appointment to set
	 */
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	/**
	 * @return the prescription
	 */
	public Prescription getPrescription() {
		return prescription;
	}
	/**
	 * @param prescription the prescription to set
	 */
	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}
	/**
	 * @return the diagnosis
	 */
	public String getDiagnosis() {
		return diagnosis;
	}
	/**
	 * @param diagnosis the diagnosis to set
	 */
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	/**
	 * @return the testResults
	 */
	public String getTestResults() {
		return testResults;
	}
	/**
	 * @param testResults the testResults to set
	 */
	public void setTestResults(String testResults) {
		this.testResults = testResults;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return the recordDate
	 */
	public Date getRecordDate() {
		return recordDate;
	}
	/**
	 * @param recordDate the recordDate to set
	 */
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	
	
	

}
