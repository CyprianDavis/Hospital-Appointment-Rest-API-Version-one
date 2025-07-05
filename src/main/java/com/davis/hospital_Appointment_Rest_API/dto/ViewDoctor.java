package com.davis.hospital_Appointment_Rest_API.dto;

/**
 * Data Transfer Object (DTO) representing a doctor for view operations in the Hospital Appointment System.
 * <p>
 * This class provides a simplified, read-optimized representation of doctor information
 * suitable for displaying doctor details in user interfaces or API responses.
 * It contains only the essential fields needed for viewing doctor information,
 * excluding sensitive or operational data.
 * </p>
 *
 * <p><b>Usage:</b>
 * <ul>
 *   <li>Used when returning doctor information to clients</li>
 *   <li>Optimized for read operations (GET requests)</li>
 *   <li>Contains formatted data ready for display</li>
 * </ul>
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-03
 */
public class ViewDoctor {
    
    /**
     * The unique identifier of the doctor in the system.
     * <p>
     * This ID corresponds to the user ID from the authentication system.
     * Format: UUID string representation.
     * </p>
     */
    private String userId;
    
    /**
     * The doctor's surname or family name.
     * <p>
     * Required field. Minimum length: 2 characters.
     * Example: "Smith"
     * </p>
     */
    private String surName;
    
    /**
     * The doctor's given (first) name.
     * <p>
     * Required field. Minimum length: 2 characters.
     * Example: "John"
     * </p>
     */
    private String givenName;
    
    /**
     * The doctor's middle name(s), if any.
     * <p>
     * Optional field. May contain multiple names separated by spaces.
     * Example: "Robert James"
     * </p>
     */
    private String otherName;
    
    /**
     * The doctor's medical specialization area.
     * <p>
     * Must be one of the hospital's recognized specializations.
     * Examples: "Cardiology", "Pediatrics", "Neurology"
     * </p>
     */
    private String specialization;
    
    /**
     * The doctor's official medical license number.
     * <p>
     * Format: "MED-" followed by 6 digits.
     * Example: "MED-123456"
     * </p>
     */
    private String license_number;
    
    /**
     * The standard consultation fee charged by this doctor.
     * <p>
     * Represented as a positive decimal number in the system's base currency.
     * Example: 50.00 (for $50.00)
     * </p>
     */
    private double consulation_fee;
    
    /**
     * The name of the department where the doctor practices.
     * <p>
     * This is the display name of the department, not the internal ID.
     * Example: "Cardiology Department"
     * </p>
     */
    private String department;
    
    /**
     * The doctor's professional email address.
     * <p>
     * Must follow standard email format.
     * Example: "dr.smith@hospital.com"
     * </p>
     */
    private String email;
    /**
     * The doctor's mobile contact
     */
    private String contact;

    /**
     * Constructs a new ViewDoctor instance with all required fields.
     * 
     * @param userId the unique doctor identifier (UUID)
     * @param surName the doctor's surname/family name
     * @param givenName the doctor's given/first name
     * @param otherName the doctor's middle name(s) (optional)
     * @param specialization the doctor's medical specialty
     * @param license_number the official license number (MED-XXXXXX format)
     * @param consulation_fee the standard consultation fee (positive value)
     * @param department the department name where the doctor practices
     * @param email the doctor's professional email address
     */
    public ViewDoctor(String userId, String surName, String givenName, String otherName, String specialization,
            String license_number, double consulation_fee, String department, String email) {
        this.userId = userId;
        this.surName = surName;
        this.givenName = givenName;
        this.otherName = otherName;
        this.specialization = specialization;
        this.license_number = license_number;
        this.consulation_fee = consulation_fee;
        this.department = department;
        this.email = email;
    }

    /**
     * Gets the unique doctor identifier.
     * @return the userId as a UUID string
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the unique doctor identifier.
     * @param userId the UUID string to set
     * @throws IllegalArgumentException if userId is null or empty
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the doctor's surname/family name.
     * @return the surname
     */
    public String getSurName() {
        return surName;
    }

    /**
     * Sets the doctor's surname/family name.
     * @param surName the surname to set
     * @throws IllegalArgumentException if surName is null or shorter than 2 characters
     */
    public void setSurName(String surName) {
        this.surName = surName;
    }

    /**
     * Gets the doctor's given (first) name.
     * @return the given name
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * Sets the doctor's given (first) name.
     * @param givenName the given name to set
     * @throws IllegalArgumentException if givenName is null or shorter than 2 characters
     */
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    /**
     * Gets the doctor's middle name(s).
     * @return the middle name(s), or null if not specified
     */
    public String getOtherName() {
        return otherName;
    }

    /**
     * Sets the doctor's middle name(s).
     * @param otherName the middle name(s) to set (optional)
     */
    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    /**
     * Gets the doctor's medical specialization.
     * @return the specialization
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * Sets the doctor's medical specialization.
     * @param specialization the specialization to set
     * @throws IllegalArgumentException if specialization is null or empty
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    /**
     * Gets the doctor's license number.
     * @return the license number in MED-XXXXXX format
     */
    public String getLicense_number() {
        return license_number;
    }

    /**
     * Sets the doctor's license number.
     * @param license_number the license number to set (must be in MED-XXXXXX format)
     * @throws IllegalArgumentException if license number format is invalid
     */
    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }

    /**
     * Gets the standard consultation fee.
     * @return the consultation fee as a positive decimal number
     */
    public double getConsulation_fee() {
        return consulation_fee;
    }

    /**
     * Sets the standard consultation fee.
     * @param consulation_fee the fee to set (must be positive)
     * @throws IllegalArgumentException if fee is negative
     */
    public void setConsulation_fee(double consulation_fee) {
        this.consulation_fee = consulation_fee;
    }

    /**
     * Gets the department name where the doctor practices.
     * @return the department name
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Sets the department name where the doctor practices.
     * @param department the department name to set
     * @throws IllegalArgumentException if department is null or empty
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Gets the doctor's professional email address.
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the doctor's professional email address.
     * @param email the email address to set
     * @throws IllegalArgumentException if email is null or invalid format
     */
    public void setEmail(String email) {
        this.email = email;
    }

	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
    
}