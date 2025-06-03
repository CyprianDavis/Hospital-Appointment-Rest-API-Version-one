package com.davis.hospital_Appointment_Rest_API.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
     * Unique medical identifier for the patient 
     * @see #getPatientId()
     * @see #setPatientId(String)
     */
    @Id
    private String patientId;
    
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
        this.patientId = patientId;
        this.surName = surName;
        this.givenName = givenName;
        this.otherName = otherName;
        this.bloodGroup = bloodGroup;
    }

    /**
     * Gets the unique patient medical identifier
     * @return The patient ID
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * Sets the unique patient medical identifier
     * @param patientId The ID to set
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId;
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
}