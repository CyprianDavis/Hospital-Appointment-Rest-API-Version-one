package com.davis.hospital_Appointment_Rest_API.model;

import java.util.Set;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;

/**
 * Represents a patient entity in the Hospital Appointment System.
 * <p>
 * This class extends the base {@link User} class using JOINED inheritance strategy,
 * with patient-specific attributes including medical information and identification.
 * The discriminator value "PATIENT" identifies this subclass in the inheritance hierarchy.
 * </p>
 * 
 * <p><b>Inheritance Strategy:</b> JOINED (Class Table Inheritance)</p>
 * <p><b>Discriminator Value:</b> "PATIENT"</p>
 * <p><b>Key Attributes:</b>
 * <ul>
 *   <li>Patient medical identifier</li>
 *   <li>Full name components (surname, given name, other names)</li>
 *   <li>Blood group information</li>
 * </ul>
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-03
 * @see User
 */
@Entity
@DiscriminatorValue("PATIENT")
@PrimaryKeyJoinColumn(name = "userId") // Links to users.id
public class Patient extends User {
    
    
    /** 
     * Patient's surname/family name 
     * @see #getSurName()
     * @see #setSurName(String)
     */
    private String surName;
    
    /** 
     * Patient's given/first name 
     * @see #getGivenName()
     * @see #setGivenName(String)
     */
    private String givenName;
    
    /** 
     * Patient's middle name(s) (optional) 
     * @see #getOtherName()
     * @see #setOtherName(String)
     */
    private String otherName;
    
    /** 
     * Patient's blood group (e.g., "A+", "B-", "O+") 
     * @see #getBloodGroup()
     * @see #setBloodGroup(String)
     */
    private String bloodGroup;
    /**
     * The collection of all appointments booked by this patient.
     * <p>
     * Represents a one-to-many relationship with {@link Appointment} entity where:
     * <ul>
     *   <li>One patient can have multiple appointments</li>
     *   <li>The relationship is managed by the "patient" field in Appointment entity</li>
     *   <li>Uses Set implementation to ensure appointment uniqueness</li>
     * </ul>
     * </p>
     * 
     * <p><b>Relationship Details:</b>
     * <ul>
     *   <li>Bidirectional relationship mapped by Appointment.patient</li>
     *   <li>Appointments are automatically associated with this patient</li>
     *   <li>Cascading follows JPA default behavior</li>
     * </ul>
     * </p>
     * 
     * @see Appointment
     */
    @OneToMany(mappedBy = "patient")
    private Set<Appointment> appointments;
    /**
     * The set of medical records associated with this patient.
     * This is the inverse side of the bidirectional relationship with {@link MedicalRecord}.
     * Each medical record in this set references this patient through its {@code patient} field.
     * 
     * The relationship is mapped by the {@code patient} field in the {@link MedicalRecord} entity,
     * indicating that the foreign key is maintained on the MedicalRecord table.
     * 
     * @see MedicalRecord#patient
     */
    @OneToMany(mappedBy = "patient")
    private Set<MedicalRecord> medicalRecords;
    /**
     * The collection of prescriptions associated with this patient.
     * This represents the inverse side of the bidirectional relationship with {@link Prescription}.
     * 
     * <p>Each prescription in this set references this patient through its {@code patient} field,
     * indicating that the foreign key is maintained in the Prescription table.</p>
     * 
     * <p>This collection includes all prescriptions issued to the patient,
     * both active and historical, for complete medical record-keeping.</p>
     * 
     * @see Prescription#patient
     */
    @OneToMany(mappedBy = "patient")
    private Set<Prescription> prescriptions;
    /**
     * The collection of billing records associated with this patient.
     * This represents the inverse side of the bidirectional relationship with {@link Billing}.
     * 
     * <p>Each billing record in this set references this patient through its {@code patient} field,
     * indicating that the foreign key is maintained in the Billing table.</p>
     * 
     * <p>This collection includes all financial transactions and invoices
     * associated with the patient's medical services.</p>
     * 
     * @see Billing#patient
     */
    @OneToMany(mappedBy = "patient")
    private Set<Billing> billings;
    
    /**
     * Constructs a new Patient with complete details.
     * 
     * @param userName Unique username for authentication
     * @param passWord Encrypted password
     * @param contact Phone number in international format
     * @param district User's district/locality
     * @param street Street address component
     * @param postalCode Postal code for mail
     * @param patientId Unique medical identifier
     * @param surName Patient's surname/family name
     * @param givenName Patient's given/first name
     * @param otherName Patient's middle name(s) (optional)
     * @param bloodGroup Patient's blood group
     */
    public Patient(String userName, String passWord, String contact, String district, String street, String postalCode,
            String patientId, String surName, String givenName, String otherName, String bloodGroup) {
        super(userName, passWord, contact, district, street, postalCode);
        this.surName = surName;
        this.givenName = givenName;
        this.otherName = otherName;
        this.bloodGroup = bloodGroup;
    }

    /**
     * Gets the patient's surname/family name
     * @return The surname
     */
    public String getSurName() {
        return surName;
    }

    /**
     * Sets the patient's surname/family name
     * @param surName The surname to set
     */
    public void setSurName(String surName) {
        this.surName = surName;
    }

    /**
     * Gets the patient's given/first name
     * @return The given name
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * Sets the patient's given/first name
     * @param givenName The given name to set
     */
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    /**
     * Gets the patient's middle name(s)
     * @return Middle name(s) or null if not provided
     */
    public String getOtherName() {
        return otherName;
    }

    /**
     * Sets the patient's middle name(s)
     * @param otherName Middle name(s) (optional)
     */
    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    /**
     * Gets the patient's blood group
     * @return The blood group (e.g., "A+", "O-")
     */
    public String getBloodGroup() {
        return bloodGroup;
    }

    /**
     * Sets the patient's blood group
     * @param bloodGroup The blood group to set
     */
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

	/**
	 * @return the appointmets
	 */
	public Set<Appointment> getAppointmets() {
		return appointments;
	}

	/**
	 * @param appointmets the appointmets to set
	 */
	public void setAppointmets(Set<Appointment> appointmets) {
		this.appointments = appointmets;
	}

	/**
	 * @return the appointments
	 */
	public Set<Appointment> getAppointments() {
		return appointments;
	}

	/**
	 * @param appointments the appointments to set
	 */
	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

	/**
	 * @return the medicalRecords
	 */
	public Set<MedicalRecord> getMedicalRecords() {
		return medicalRecords;
	}

	/**
	 * @param medicalRecords the medicalRecords to set
	 */
	public void setMedicalRecords(Set<MedicalRecord> medicalRecords) {
		this.medicalRecords = medicalRecords;
	}

	/**
	 * @return the prescriptions
	 */
	public Set<Prescription> getPrescriptions() {
		return prescriptions;
	}

	/**
	 * @param prescriptions the prescriptions to set
	 */
	public void setPrescriptions(Set<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
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