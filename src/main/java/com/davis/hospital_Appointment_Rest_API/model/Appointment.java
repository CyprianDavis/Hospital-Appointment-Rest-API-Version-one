package com.davis.hospital_Appointment_Rest_API.model;

import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Represents a medical appointment in the Hospital Appointment System.
 * <p>
 * This entity connects patients with doctors at specific time slots and tracks
 * the appointment status and relevant notes.
 * </p>
 * 
 * <p><b>Relationships:</b>
 * <ul>
 *   <li>Many-to-one with {@link Doctor}</li>
 *   <li>Many-to-one with {@link Patient}</li>
 * </ul>
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-03
 * @see Doctor
 * @see Patient
 */
@Entity
public class Appointment {
    
    /**
     * Unique identifier for the appointment
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * The doctor assigned to this appointment
     */
    @ManyToOne
    @JoinColumn(name = "doctorId")
    private Doctor doctor;
    
    /**
     * The patient who booked this appointment
     */
    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;
    
    /**
     * The date of the appointment
     */
    private Date date;
    
    /**
     * The scheduled start time of the appointment
     */
    private LocalTime startTime;
    
    /**
     * The scheduled end time of the appointment
     */
    private LocalTime endTime;
    
    /**
     * Current status of the appointment (e.g., "Scheduled", "Completed", "Cancelled")
     */
    private String status;
    
    /**
     * Additional notes or comments about the appointment
     */
    private String notes;
    
    /**
     * Timestamp when the appointment was created
     */
    @Temporal(TemporalType.TIME)
    private Date createdOn;
    
    /**
     * Timestamp when the appointment was last updated
     */
    @Temporal(TemporalType.TIME)
    private Date updateOn;
    /**
     * The set of billing records associated with this appointment.
     * Represents the inverse side of the bidirectional relationship with {@link Billing}.
     * 
     * <p>Each billing record in this collection references this appointment through its 
     * {@code appointment} field, maintaining the foreign key relationship in the database.</p>
     * 
     * <p>This collection contains all financial charges generated from services 
     * provided during this specific appointment.</p>
     * 
     * @see Billing#appointment
     */
    @OneToMany(mappedBy = "appointment")
    private Set<Billing> billings = new HashSet<>();
    /**
     * Constructs a new Appointment with all required fields.
     *
     * @param doctor The doctor assigned to the appointment
     * @param patient The patient booking the appointment
     * @param date The date of the appointment
     * @param startTime The scheduled start time
     * @param endTime The scheduled end time
     * @param status The current status of the appointment
     * @param notes Additional notes about the appointment
     * @param createdOn When the appointment was created
     * @param updateOn When the appointment was last updated
     */
    public Appointment(Doctor doctor, Patient patient, Date date, LocalTime startTime, 
                     LocalTime endTime, String status, String notes, Date createdOn, 
                     Date updateOn) {
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
     * Gets the unique appointment identifier.
     * @return The appointment ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique appointment identifier.
     * @param id The ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the doctor assigned to this appointment.
     * @return The associated Doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Sets the doctor assigned to this appointment.
     * @param doctor The Doctor to assign
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * Gets the patient who booked this appointment.
     * @return The associated Patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Sets the patient who booked this appointment.
     * @param patient The Patient to assign
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Gets the appointment date.
     * @return The date of the appointment
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the appointment date.
     * @param date The date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the scheduled start time.
     * @return The start time
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Sets the scheduled start time.
     * @param startTime The start time to set
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets the scheduled end time.
     * @return The end time
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Sets the scheduled end time.
     * @param endTime The end time to set
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets the current appointment status.
     * @return The status (e.g., "Scheduled", "Completed")
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the current appointment status.
     * @param status The status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the appointment notes.
     * @return Additional notes about the appointment
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the appointment notes.
     * @param notes The notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Gets when the appointment was created.
     * @return The creation timestamp
     */
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * Sets when the appointment was created.
     * @param createdOn The creation timestamp to set
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * Gets when the appointment was last updated.
     * @return The last update timestamp
     */
    public Date getUpdateOn() {
        return updateOn;
    }

    /**
     * Sets when the appointment was last updated.
     * @param updateOn The update timestamp to set
     */
    public void setUpdateOn(Date updateOn) {
        this.updateOn = updateOn;
    }

	/**
	 * @return the billings
	 */
	public Set<Billing> getBillings() {
		return billings;
	}

	/**
	 * @param billings the billings to set
	 */
	public void setBillings(Set<Billing> billings) {
		this.billings = billings;
	}
    
}