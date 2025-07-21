package com.davis.hospital_Appointment_Rest_API.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
 *   <li>Demographic information (gender, date of birth)</li>
 *   <li>Associated medical records, appointments, prescriptions, and billings</li>
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
     * Patient's gender (typically "Male", "Female", or other gender identities)
     * @see #getGender()
     * @see #setGender(String)
     */
    private String gender;
    
    /**
     * Patient's date of birth in ISO format (YYYY-MM-DD)
     * @see #getDateOfBirth()
     * @see #setDateOfBirth(String)
     */
    private String dateOfBirth;
    
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
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private Set<Appointment> appointments = new HashSet<>();
    
    /**
     * The set of medical records associated with this patient.
     * This is the inverse side of the bidirectional relationship with {@link MedicalRecord}.
     * Each medical record in this set references this patient through its {@code patient} field.
     * 
     * <p>The relationship is mapped by the {@code patient} field in the {@link MedicalRecord} entity,
     * indicating that the foreign key is maintained on the MedicalRecord table.</p>
     * 
     * @see MedicalRecord#patient
     */
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private Set<MedicalRecord> medicalRecords = new HashSet<>();
    
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
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private Set<Prescription> prescriptions = new HashSet<>();
    
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
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private Set<Billing> billings = new HashSet<>();
    
    /**
     * Constructs a new Patient with complete details.
     * 
     * @param userName Unique username for authentication
     * @param passWord Encrypted password
     * @param contact Phone number in international format
     * @param district User's district/locality
     * @param street Street address component
     * @param postalCode Postal code for mail
     * @param surName Patient's surname/family name
     * @param givenName Patient's given/first name
     * @param otherName Patient's middle name(s) (optional)
     * @param bloodGroup Patient's blood group
     */
    public Patient(String userName, String passWord, String contact, String district, String street, String postalCode,
            String surName, String givenName, String otherName, String bloodGroup) {
        super(userName, passWord, contact, district, street, postalCode);
        this.surName = surName;
        this.givenName = givenName;
        this.otherName = otherName;
        this.bloodGroup = bloodGroup;
    }

    /**
     * Default no-argument constructor required by JPA.
     */
    public Patient() {
        // JPA requires an empty constructor
    }

    /**
     * Gets the patient's surname/family name.
     * 
     * @return The surname as a String
     */
    public String getSurName() {
        return surName;
    }

    /**
     * Sets the patient's surname/family name.
     * 
     * @param surName The surname to set (non-null)
     */
    public void setSurName(String surName) {
        this.surName = surName;
    }

    /**
     * Gets the patient's given/first name.
     * 
     * @return The given name as a String
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * Sets the patient's given/first name.
     * 
     * @param givenName The given name to set (non-null)
     */
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    /**
     * Gets the patient's middle name(s).
     * 
     * @return Middle name(s) as a String, or null if not provided
     */
    public String getOtherName() {
        return otherName;
    }

    /**
     * Sets the patient's middle name(s).
     * 
     * @param otherName Middle name(s) (optional, may be null)
     */
    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    /**
     * Gets the patient's blood group.
     * 
     * @return The blood group as a String (e.g., "A+", "O-")
     */
    public String getBloodGroup() {
        return bloodGroup;
    }

    /**
     * Sets the patient's blood group.
     * 
     * @param bloodGroup The blood group to set (standard format recommended)
     */
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    /**
     * Gets all appointments associated with this patient.
     * 
     * @return A Set of Appointment entities
     * @see Appointment
     */
    public Set<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * Sets the appointments for this patient.
     * 
     * @param appointments The Set of Appointment entities to associate
     * @see Appointment
     */
    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    /**
     * Gets all medical records associated with this patient.
     * 
     * @return A Set of MedicalRecord entities
     * @see MedicalRecord
     */
    public Set<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    /**
     * Sets the medical records for this patient.
     * 
     * @param medicalRecords The Set of MedicalRecord entities to associate
     * @see MedicalRecord
     */
    public void setMedicalRecords(Set<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    /**
     * Gets all prescriptions associated with this patient.
     * 
     * @return A Set of Prescription entities
     * @see Prescription
     */
    public Set<Prescription> getPrescriptions() {
        return prescriptions;
    }

    /**
     * Sets the prescriptions for this patient.
     * 
     * @param prescriptions The Set of Prescription entities to associate
     * @see Prescription
     */
    public void setPrescriptions(Set<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    /**
     * Gets all billing records associated with this patient.
     * 
     * @return A Set of Billing entities
     * @see Billing
     */
    public Set<Billing> getBillings() {
        return billings;
    }

    /**
     * Sets the billing records for this patient.
     * 
     * @param billings The Set of Billing entities to associate
     * @see Billing
     */
    public void setBillings(Set<Billing> billings) {
        this.billings = billings;
    }

    /**
     * Gets the patient's gender.
     * 
     * @return The gender as a String
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the patient's gender.
     * 
     * @param gender The gender to set (e.g., "Male", "Female")
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the patient's date of birth.
     * 
     * @return The date of birth in ISO format (YYYY-MM-DD)
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the patient's date of birth.
     * 
     * @param dateOfBirth The date of birth in ISO format (YYYY-MM-DD)
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}