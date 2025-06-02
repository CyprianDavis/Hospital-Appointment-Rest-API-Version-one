package com.davis.hospital_Appointment_Rest_API.model;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

/**
 * Abstract base class representing a user in the Hospital Appointment System.
 * <p>
 * This entity serves as the parent class in a JOINED inheritance strategy,
 * with concrete implementations for different user types (Patient, Doctor, Admin).
 * The discriminator column "user_type" identifies the specific user subclass.
 * </p>
 * 
 * <p><b>Inheritance Strategy:</b> JOINED (separate tables for parent and subclasses)</p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-03
 * @see Patient
 * @see Doctor
 * @see Admin
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public abstract class User {
    
    /** Unique identifier for the user */
	@Id
    private String userId;
    
    /** Unique username for authentication */
    private String userName;
    
    /** Encrypted password (should be hashed in production) */
    private String passWord;
    
    /** Contact phone number in international format (e.g., +256...) */
    private String contact;
    
    /** Unique email address for communication */
    private String email;
    
    /** 
     * User type discriminator (automatically managed by JPA)
     * @see #getUserType() 
     */
    private String userType;
    
    /** District/Locality of the user's address */
    private String district;
    
    /** Street address component */
    private String street;
    
    /** Postal code for physical mail */
    @Column(name = "postal_code")
    private String postalCode;
    
    /** Timestamp when user account was created */
    private Date createdOn;
    
    /** Timestamp of last profile update */
    private Date lastUpdated;

    /**
     * Constructs a new User with basic information.
     * 
     * @param userName Unique username for authentication
     * @param passWord Encrypted password
     * @param contact Phone number in international format
     * @param district User's district/locality
     * @param street Street address component
     * @param postalCode Postal code for mail
     */
    public User(String userName, String passWord, String contact, 
               String district, String street, String postalCode) {
        this.userName = userName;
        this.passWord = passWord;
        this.contact = contact;
        this.district = district;
        this.street = street;
        this.postalCode = postalCode;
    }

    /**
     * Gets the unique user identifier
     * @return The user ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the unique user identifier
     * @param userId The ID to set (typically auto-generated)
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the authentication username
     * @return The username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the authentication username
     * @param userName The username to set
     */
    public void setUserName(String userName) {
        
        this.userName = userName;
    }

    /**
     * Gets the encrypted password
     * @return The password hash
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * Sets the encrypted password
     * @param passWord The password hash to set
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * Gets the contact phone number
     * @return Phone number in international format
     */
    public String getContact() {
        return contact;
    }

    /**
     * Sets the contact phone number
     * @param contact Phone number in international format (e.g., +256...)
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * Gets the district/locality
     * @return The district name
     */
    public String getDistrict() {
        return district;
    }

    /**
     * Sets the district/locality
     * @param district The district name
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * Gets the street address
     * @return The street name
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street address
     * @param street The street name
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets the postal code
     * @return The postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postal code
     * @param postalCode The postal code
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Gets the email address
     * @return The email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address
     * @param email The email to set (must be valid format)
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user type discriminator
     * @return The user type (e.g., "PATIENT", "DOCTOR", "ADMIN")
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Sets the user type discriminator
     * @param userType The user type to set
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * Gets the account creation timestamp
     * @return Date when user was created
     */
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * Sets the account creation timestamp
     * @param createdOn Creation date (typically auto-set)
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * Gets the last update timestamp
     * @return Date of last profile update
     */
    public Date getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Sets the last update timestamp
     * @param lastUpdated Date of last update
     */
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}