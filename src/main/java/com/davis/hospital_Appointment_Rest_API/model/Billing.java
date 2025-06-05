package com.davis.hospital_Appointment_Rest_API.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Represents a billing record in the hospital appointment system.
 * Contains financial information related to patient appointments and services rendered.
 * 
 * <p>Each billing record is associated with a specific {@link Patient} and {@link Appointment},
 * tracking payment amounts, status, and due dates for medical services.</p>
 */
@Entity
public class Billing {
    /**
     * Unique identifier for the billing record (auto-generated)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * The patient who is responsible for this billing record
     */
    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;
    
    /**
     * The appointment associated with these charges
     */
    @ManyToOne
    @JoinColumn(name="appointmentId")
    private Appointment appointment;
    
    /**
     * The total amount due for services rendered
     */
    private double amount;
    
    /**
     * Current status of payment (e.g., "PAID", "PENDING", "OVERDUE")
     */
    private String paymentStatus;
    
    /**
     * Date when the invoice was generated
     */
    private Date invoiceDate;
    
    /**
     * Date by which payment should be received
     */
    private Date dueDate;

    // All existing constructors and methods below remain exactly the same
    // ...
}
	/**
	 * @param patient
	 * @param appointment
	 * @param amount
	 * @param paymentStatus
	 * @param invoiceDate
	 * @param dueDate
	 */
	public Billing(Patient patient, Appointment appointment, double amount, String paymentStatus, Date invoiceDate,
			Date dueDate) {
		this.patient = patient;
		this.appointment = appointment;
		this.amount = amount;
		this.paymentStatus = paymentStatus;
		this.invoiceDate = invoiceDate;
		this.dueDate = dueDate;
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
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return the paymentStatus
	 */
	public String getPaymentStatus() {
		return paymentStatus;
	}
	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	/**
	 * @return the invoiceDate
	 */
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	/**
	 * @param invoiceDate the invoiceDate to set
	 */
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	
	

}
