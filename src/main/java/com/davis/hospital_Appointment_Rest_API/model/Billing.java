package com.davis.hospital_Appointment_Rest_API.model;

import java.util.Date;



public class Billing {
	private Long id;
	private Patient patient;
	private Appointment appointment;
	private double amount;
	private String paymentStatus;
	private Date invoiceDate;
	private Date dueDate;
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
		super();
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
