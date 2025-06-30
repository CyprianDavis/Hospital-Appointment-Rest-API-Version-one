package com.davis.hospital_Appointment_Rest_API.model;

import java.util.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Represents a medical prescription in the hospital appointment system.
 * Contains medication details, administration instructions, and associated
 * patient and doctor information.
 */
@Entity
public class Prescription {
    /**
     * Unique identifier for the prescription (auto-generated)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionId;
    
    /**
     * The patient who is prescribed the medication
     */
    @ManyToOne
    @JoinColumn(name = " PatientId")
    private Patient patient;
    
    /**
     * The doctor who issued the prescription
     */
    @ManyToOne
    @JoinColumn(name = "DoctorId")
    private Doctor doctor;
    
    /**
     * The amount of medication to be taken at one time
     */
    private String dosage;
    
    /**
     * How often the medication should be taken
     */
    private String frequency;
    
    /**
     * Method of administering the medication (oral, IV, topical, etc.)
     */
    private String routeOfAdministration;
    
    /**
     * Length of time the medication should be taken
     */
    private String duration;
    
    /**
     * Total amount of medication prescribed
     */
    private int quantity;
    
    /**
     * Additional instructions or comments about the prescription
     */
    private String notes;
    
    /**
     * Date and time when the prescription was originally created
     */
    @Temporal(TemporalType.TIME)
    private Date createdOn;
    
    /**
     * Date and time when the prescription was last modified
     */
    @Temporal(TemporalType.TIME)
    private Date modifiedDate;
	/**
	 * @param patient
	 * @param doctor
	 * @param dosage
	 * @param frequency
	 * @param routeOfAdministration
	 * @param duration
	 * @param quantity
	 * @param notes
	 * @param createdOn
	 * @param modifiedDate
	 */
	public Prescription(Patient patient, Doctor doctor, String dosage, String frequency, String routeOfAdministration,
			String duration, int quantity, String notes, Date createdOn, Date modifiedDate) {
		this.patient = patient;
		this.doctor = doctor;
		this.dosage = dosage;
		this.frequency = frequency;
		this.routeOfAdministration = routeOfAdministration;
		this.duration = duration;
		this.quantity = quantity;
		this.notes = notes;
		this.createdOn = createdOn;
		this.modifiedDate = modifiedDate;
	}
	public Prescription() {}
	/**
	 * @return the prescriptionId
	 */
	public Long getPrescriptionId() {
		return prescriptionId;
	}
	/**
	 * @param prescriptionId the prescriptionId to set
	 */
	public void setPrescriptionId(Long prescriptionId) {
		this.prescriptionId = prescriptionId;
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
	 * @return the dosage
	 */
	public String getDosage() {
		return dosage;
	}
	/**
	 * @param dosage the dosage to set
	 */
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	/**
	 * @return the frequency
	 */
	public String getFrequency() {
		return frequency;
	}
	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	/**
	 * @return the routeOfAdministration
	 */
	public String getRouteOfAdministration() {
		return routeOfAdministration;
	}
	/**
	 * @param routeOfAdministration the routeOfAdministration to set
	 */
	public void setRouteOfAdministration(String routeOfAdministration) {
		this.routeOfAdministration = routeOfAdministration;
	}
	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/**
	 * @return the createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
	}
	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	/**
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}
	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
	

}
