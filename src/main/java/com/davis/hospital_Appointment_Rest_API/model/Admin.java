package com.davis.hospital_Appointment_Rest_API.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;

/**
 * Represents an administrative user in the Hospital Appointment System using Class Table Inheritance.
 * <p>
 * This entity extends the base {@link User} class and is mapped to its own table in the database,
 * while maintaining a foreign key relationship with the parent Users table through the userId field.
 * The discriminator value "ADMIN" identifies this subclass in the inheritance hierarchy.
 * </p>
 * 
 * <p><b>Inheritance Strategy:</b> JOINED (Class Table Inheritance)</p>
 * <p><b>Discriminator Value:</b> "ADMIN"</p>
 * <p><b>Table Structure:</b>
 * <ul>
 *   <li>Primary table: users (contains common fields)</li>
 *   <li>Child table: admin (contains admin-specific fields)</li>
 * </ul>
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-03
 * @see User
 */
@Entity
@DiscriminatorValue("ADMIN")
@PrimaryKeyJoinColumn(name = "userId") // Links to users.id
public class Admin extends User {
    
    /** 
     * Unique identifier for the admin record (matches user_id in parent table)
     * @see #getId()
     * @see #setId(String) 
     */
    @Id
    private String id;
    
    /** 
     * Administrator's surname/family name 
     * @see #getSurName()
     * @see #setSurName(String)
     */
    private String surName;
    
    /** 
     * Administrator's given/first name 
     * @see #getGivenName()
     * @see #setGivenName(String)
     */
    private String givenName;
    
    /** 
     * Administrator's middle name(s) (optional) 
     * @see #getOtherName()
     * @see #setOtherName(String)
     */
    private String otherName;
    
    /** 
     * Privilege level (e.g., "SUPER_ADMIN", "DEPARTMENT_ADMIN") 
     * @see #getAccessLevel()
     * @see #setAccessLevel(String)
     */
    private String accessLevel;
    
    /**
     * Constructs a new Admin entity with Class Table Inheritance.
     * 
     * @param userName Unique username for authentication (inherited from User)
     * @param passWord Encrypted password (inherited from User)
     * @param contact Phone number (inherited from User)
     * @param district Address district (inherited from User)
     * @param street Address street (inherited from User)
     * @param postalCode Postal code (inherited from User)
     * @param id Unique identifier (matches user_id in parent table)
     * @param surName Administrator's surname
     * @param givenName Administrator's given name
     * @param otherName Administrator's middle name(s) (optional)
     * @param accessLevel Privilege level (e.g., "SUPER_ADMIN")
     */
    public Admin(String userName, String passWord, String contact, 
                String district, String street, String postalCode,
                String id, String surName, String givenName, 
                String otherName, String accessLevel) {
        super(userName, passWord, contact, district, street, postalCode);
        
      
        
        this.id = id;
        this.surName = surName;
        this.givenName = givenName;
        this.otherName = otherName;
        this.accessLevel = accessLevel;
    }

    /**
     * Gets the admin ID which corresponds to the user_id in parent table
     * @return The admin ID (matches parent user_id)
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the admin ID which must match the user_id in parent table
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the administrator's surname/family name
     * @return The surname
     */
    public String getSurName() {
        return surName;
    }

    /**
     * Sets the administrator's surname/family name
    
     */
    public void setSurName(String surName) {
        
        this.surName = surName;
    }

    /**
     * Gets the administrator's given/first name
     * @return The given name
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * Sets the administrator's given/first name
     
     */
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    /**
     * Gets the administrator's middle name(s)
     * @return Middle name(s) or null if not provided
     */
    public String getOtherName() {
        return otherName;
    }

    /**
     * Sets the administrator's middle name(s)
     * @param otherName Middle name(s) (optional, can be null)
     */
    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    /**
     * Gets the administrator's access privilege level
     * @return The access level (e.g., "SUPER_ADMIN")
     */
    public String getAccessLevel() {
        return accessLevel;
    }

    /**
     * Sets the administrator's access privilege level
     */
    public void setAccessLevel(String accessLevel) {
       
        this.accessLevel = accessLevel;
    }
}