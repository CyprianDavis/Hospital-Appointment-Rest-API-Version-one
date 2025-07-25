package com.davis.hospital_Appointment_Rest_API.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

/**
 * Represents a hospital department in the Hospital Appointment System.
 * <p>
 * This entity models a department within the hospital, containing information
 * about its location, contact details, and the doctor who serves as the
 * head of the department.
 * </p>
 * 
 * <p><b>Relationships:</b>
 * <ul>
 *   <li>One-to-one with Doctor (head of department)</li>
 * </ul>
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-03
 * @see Doctor
 */
@Entity
public class Department {
    /** Unique identifier for the department */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departId;
    
    /** Name of the department (e.g., "Cardiology") */
    private String name;
    
    /** Detailed description of the department's services */
    private String description;
    
    /** Physical location code within the hospital (e.g., "BLDG-A-2F") */
    @Column(name="location_code")
    private String locationCode;
    
    /** Direct contact number for the department */
    private String contact;
    
    /** Doctor serving as the head of this department */
    @OneToOne
    @JoinColumn(name ="HOD")
    private Doctor headOfDepart;
    
    /** Timestamp when the department record was created */
    @Column(name=" Created_at",updatable = false)
    @CreationTimestamp
    
    private LocalDateTime createdOn;
    
    /** Timestamp when the department record was last updated */
    @UpdateTimestamp
    @Column(name="lastUpdated")
    private LocalDateTime updatedOn;
    @JsonIgnore
    @OneToMany(mappedBy = "department", fetch =FetchType.LAZY )
    private Set<Doctor>doctors = new HashSet<>();
    
    /**
     * Constructs a new Department with complete details.
     * 
     * @param department Name of the department
     * @param description Description of services
     * @param locactionCode Physical location identifier
     * @param contact Direct contact information
     * @param headOfDepart Head doctor of the department
     * @param createdOn Creation timestamp
     * @param updatedOn Last update timestamp
     */
    public Department(String department, String description, String locationCode, String contact, Doctor headOfDepart,
            LocalDateTime createdOn, LocalDateTime updatedOn) {
        this.name = department;
        this.description = description;
        this.locationCode = locationCode;
        this.contact = contact;
        this.headOfDepart = headOfDepart;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }
    public Department() { }
    
    /**
     * Gets the unique department identifier
     * @return The department ID
     */
    public Long getDepartId() {
        return departId;
    }
    
    /**
     * Sets the unique department identifier
     * @param departId The ID to set
     */
    public void setDepartId(Long departId) {
        this.departId = departId;
    }
    
    
    
    /**
     * Gets the department description
     * @return The description of services
     */
    public String getDescription() {
        return description;
    }
    
    /**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
     * Sets the department description
     * @param description The service description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Gets the location code
     * @return The physical location identifier
     */
    public String getLocationCode() {
        return locationCode;
    }
    
    /**
     * Sets the location code
     * @param locactionCode The location identifier to set
     */
    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }
    
    /**
     * Gets the contact information
     * @return The department's contact details
     */
    public String getContact() {
        return contact;
    }
    
    /**
     * Sets the contact information
     * @param contact The contact details to set
     */
    public void setContact(String contact) {
        this.contact = contact;
    }
    
    /**
     * Gets the head of department
     * @return The doctor serving as department head
     */
    public Doctor getHeadOfDepart() {
        return headOfDepart;
    }
    
    /**
     * Sets the head of department
     * @param headOfDepart The doctor to assign as department head
     */
    public void setHeadOfDepart(Doctor headOfDepart) {
        this.headOfDepart = headOfDepart;
    }
    
    /**
     * Gets the creation timestamp
     * @return When the department record was created
     */
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }
    
    /**
     * Sets the creation timestamp
     * @param createdOn When the department was created
     */
    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
    
    /**
     * Gets the last update timestamp
     * @return When the department was last updated
     */
    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }
    
    /**
     * Sets the last update timestamp
     * @param updatedOn When the department was last updated
     */
    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

	/**
	 * @return the doctors
	 */
	public Set<Doctor> getDoctors() {
		return doctors;
	}

	/**
	 * @param doctors the doctors to set
	 */
	public void setDoctors(Set<Doctor> doctors) {
		this.doctors = doctors;
	}
    
}