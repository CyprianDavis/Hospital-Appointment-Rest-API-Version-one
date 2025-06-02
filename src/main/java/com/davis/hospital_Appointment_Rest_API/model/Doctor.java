package com.davis.hospital_Appointment_Rest_API.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;

/**
 * Represents a medical doctor in the Hospital Appointment System.
 * <p>
 * This entity extends the base {@link User} class and is marked with the
 * discriminator value "DOCTOR" for JPA's inheritance strategy. Doctors have
 * specialized medical attributes in addition to basic user properties.
 * </p>
 * 
 * <p><b>Inheritance Strategy:</b> JOINED (Class Table Inheritance)</p>
 * <p><b>Discriminator Value:</b> "DOCTOR"</p>
 * <p><b>Table Structure:</b>
 * <ul>
 *   <li>Primary table: users (contains common user fields)</li>
 *   <li>Child table: doctor (contains doctor-specific fields)</li>
 * </ul>
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-03
 * @see User
 * @see Department
 */
@Entity
@DiscriminatorValue("DOCTOR")
@PrimaryKeyJoinColumn(name = "user_id") // Links to users.id
public class Doctor extends User {
    
    /** Unique identifier for the doctor record */
    @Id
    private String doctorId;
    
    /** Doctor's surname/family name */
    private String surName;
    
    /** Doctor's given/first name */
    private String givenName;
    
    /** Doctor's middle name(s) (optional) */
    private String otherName;
    
    /** Medical specialization area (e.g., "Cardiology") */
    private String specialization;
    
    /** Official medical license number */
    private String license_number;
    
    /** Standard consultation fee in local currency */
    private double consulation_fee;
    
    /** Department where the doctor practices */
    private Department department;
    
    /**
     * Constructs a new Doctor with basic user information.
     * 
     * @param userName Unique username for authentication
     * @param passWord Encrypted password
     * @param contact Phone number in international format
     * @param district User's district/locality
     * @param street Street address component
     * @param postalCode Postal code for mail
     */
    public Doctor(String userName, String passWord, String contact, String district, String street, String postalCode) {
        super(userName, passWord, contact, district, street, postalCode);
    }

    /**
     * Gets the unique doctor identifier
     * @return The doctor ID
     */
    public String getDoctorId() {
        return doctorId;
    }

    /**
     * Sets the unique doctor identifier
     * @param doctorId The ID to set
     */
    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * Gets the doctor's surname/family name
     * @return The surname
     */
    public String getSurName() {
        return surName;
    }

    /**
     * Sets the doctor's surname/family name
     * @param surName The surname to set
     */
    public void setSurName(String surName) {
        this.surName = surName;
    }

    /**
     * Gets the doctor's given/first name
     * @return The given name
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * Sets the doctor's given/first name
     * @param givenName The given name to set
     */
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    /**
     * Gets the doctor's middle name(s)
     * @return Middle name(s) or null if not provided
     */
    public String getOtherName() {
        return otherName;
    }

    /**
     * Sets the doctor's middle name(s)
     * @param otherName Middle name(s) (optional)
     */
    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    /**
     * Gets the doctor's medical specialization
     * @return The specialization area
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * Sets the doctor's medical specialization
     * @param specialization The specialization to set
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    /**
     * Gets the doctor's license number
     * @return The official license number
     */
    public String getLicense_number() {
        return license_number;
    }

    /**
     * Sets the doctor's license number
     * @param license_number The official license number to set
     */
    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }

    /**
     * Gets the standard consultation fee
     * @return The fee amount in local currency
     */
    public double getConsulation_fee() {
        return consulation_fee;
    }

    /**
     * Sets the standard consultation fee
     * @param consulation_fee The fee amount to set (must be positive)
     */
    public void setConsulation_fee(double consulation_fee) {
        this.consulation_fee = consulation_fee;
    }

    /**
     * Gets the associated department
     * @return The Department where the doctor practices
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Sets the associated department
     * @param department The Department to assign
     */
    public void setDepartment(Department department) {
        this.department = department;
    }
}