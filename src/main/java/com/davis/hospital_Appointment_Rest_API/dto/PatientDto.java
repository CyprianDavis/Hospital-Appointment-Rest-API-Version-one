package com.davis.hospital_Appointment_Rest_API.dto;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for patient information.
 * <p>
 * This class represents a simplified view of patient data used for transferring
 * information between layers of the application, particularly between the
 * controller and service layers. It contains essential patient details while
 * excluding sensitive or complex entity relationships.
 * </p>
 * 
 * <p><b>Key Differences from Patient Entity:</b>
 * <ul>
 *   <li>Flattened structure (combined name fields)</li>
 *   <li>Excludes entity relationships (appointments, medical records, etc.)</li>
 *   <li>Simplified for API consumption</li>
 * </ul>
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @since 2025-07-22
 */
public class PatientDto {
    
    /**
     * Unique username identifier for the patient
     */
    private String userName;
    
    /**
     * Full name of the patient (combined surname, given name, and other names)
     */
    private String name;
    
    /**
     * Patient's blood group in standard format (e.g., "A+", "B-", "O+")
     */
    private String bloodGroup;
    
    /**
     * Contact phone number in international format
     */
    private String contact;
    
    /**
     * Patient's email address
     */
    private String email;
    
    /**
     * Postal/ZIP code for patient's address
     */
    private String postalCode;
    
    /**
     * Patient's gender (typically "Male", "Female", or other identities)
     */
    private String gender;
    
    /**
     * Patient's date of birth in ISO format (YYYY-MM-DD)
     */
    private LocalDate dateOfBirth;
    
    /**
     * Constructs a new PatientDto with all required fields.
     * 
     * @param userName Unique username identifier
     * @param name Full name of the patient
     * @param bloodGroup Blood group in standard format
     * @param contact Contact phone number
     * @param email Email address
     * @param postalCode Postal/ZIP code
     * @param gender Gender identity
     * @param dateOfBirth Date of birth
     */
    public PatientDto(String userName, String name, String bloodGroup,
            String contact, String email, String postalCode, String gender, 
            LocalDate dateOfBirth) {
        this.userName = userName;
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.contact = contact;
        this.email = email;
        this.postalCode = postalCode;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the unique username identifier.
     * 
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the unique username identifier.
     * 
     * @param userName the userName to set (non-null)
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the patient's full name.
     * 
     * @return the combined name (surname + given name + other names)
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the patient's full name.
     * 
     * @param name the combined name to set (non-null)
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the patient's blood group.
     * 
     * @return the bloodGroup in standard format
     */
    public String getBloodGroup() {
        return bloodGroup;
    }

    /**
     * Sets the patient's blood group.
     * 
     * @param bloodGroup the blood group to set (standard format recommended)
     */
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    /**
     * Gets the patient's contact number.
     * 
     * @return the contact phone number
     */
    public String getContact() {
        return contact;
    }

    /**
     * Sets the patient's contact number.
     * 
     * @param contact the phone number to set (international format recommended)
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * Gets the patient's email address.
     * 
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the patient's email address.
     * 
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the patient's postal code.
     * 
     * @return the postal/ZIP code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the patient's postal code.
     * 
     * @param postalCode the postal/ZIP code to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Gets the patient's gender.
     * 
     * @return the gender identity
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the patient's gender.
     * 
     * @param gender the gender identity to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the patient's date of birth.
     * 
     * @return the date of birth in ISO format
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the patient's date of birth.
     * 
     * @param dateOfBirth the date of birth to set (ISO format recommended)
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}