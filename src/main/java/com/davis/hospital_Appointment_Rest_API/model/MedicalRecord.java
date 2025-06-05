package com.davis.hospital_Appointment_Rest_API.model;

import java.util.Date;



public class MedicalRecord {
	private String id;
	private Patient patient;
	private Doctor doctor;
	private Appointment appointment;
	private Prescription prescription;
	private String diagnosis;
	private String testResults;
	private String note;
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
