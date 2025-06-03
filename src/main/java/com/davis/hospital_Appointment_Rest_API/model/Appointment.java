package com.davis.hospital_Appointment_Rest_API.model;


import java.time.LocalTime;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "doctorId")
	private Doctor doctor;
	@ManyToOne
	@JoinColumn(name="patientId")
	private Patient patient;
	private Date date;
	private LocalTime startTime;
	private LocalTime endTime;
	private String status;
	private String notes;
	@Temporal(TemporalType.TIME)
	private Date createdOn;
	@Temporal(TemporalType.TIME)
	private Date updateOn;
	/**
	 * @param doctor
	 * @param patient
	 * @param date
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @param notes
	 * @param createdOn
	 * @param updateOn
	 */
	public Appointment(Doctor doctor, Patient patient, Date date, LocalTime startTime, LocalTime endTime, String status,
			String notes, Date createdOn, Date updateOn) {
		this.doctor = doctor;
		this.patient = patient;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.notes = notes;
		this.createdOn = createdOn;
		this.updateOn = updateOn;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the startTime
	 */
	public LocalTime getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public LocalTime getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @return the updateOn
	 */
	public Date getUpdateOn() {
		return updateOn;
	}
	/**
	 * @param updateOn the updateOn to set
	 */
	public void setUpdateOn(Date updateOn) {
		this.updateOn = updateOn;
	}
	
	
	
	
	

}
