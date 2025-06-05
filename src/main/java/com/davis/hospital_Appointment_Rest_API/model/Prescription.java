package com.davis.hospital_Appointment_Rest_API.model;

import java.util.Date;


public class Prescription {
	private Long prescriptionId;
	private Patient patient;
	private Doctor doctor;
	private String dosage;
	private String frequency;
	private String routeOfAdministration;
	private String duration;
	private int quantity;
	private String notes;
	private Date createdOn;
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
