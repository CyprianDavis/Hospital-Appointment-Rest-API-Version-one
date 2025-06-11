package com.davis.hospital_Appointment_Rest_API.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
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
    private String departId;
    
    /** Name of the department (e.g., "Cardiology") */
    private String name;
    
    /** Detailed description of the department's services */
    private String description;
    
    /** Physical location code within the hospital (e.g., "BLDG-A-2F") */
    private String locactionCode;
    
    /** Direct contact number for the department */
    private String contact;
    
    /** Doctor serving as the head of this department */
    @OneToOne
    @JoinColumn(name ="HOD")
    private Doctor headOfDepart;
    
    /** Timestamp when the department record was created */
    private Date createdOn;
    
    /** Timestamp when the department record was last updated */
    private Date updatedOn;
    @OneToMany(mappedBy = "department")
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
    public Department(String department, String description, String locactionCode, String contact, Doctor headOfDepart,
            Date createdOn, Date updatedOn) {
        this.name = department;
        this.description = description;
        this.locactionCode = locactionCode;
        this.contact = contact;
        this.headOfDepart = headOfDepart;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }
    
    /**
     * Gets the unique department identifier
     * @return The department ID
     */
    public String getDepartId() {
        return departId;
    }
    
    /**
     * Sets the unique department identifier
     * @param departId The ID to set
     */
    public void setDepartId(String departId) {
        this.departId = departId;
    }
    
    /**
     * Gets the department name
     * @return The department name
     */
    public String getDepartment() {
        return name;
    }
    
    /**
     * Sets the department name
     * @param department The name to set
     */
    public void setDepartment(String department) {
        this.name = department;
    }
    
    /**
     * Gets the department description
     * @return The description of services
     */
    public String getDescription() {
        return description;
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
    public String getLocactionCode() {
        return locactionCode;
    }
    
    /**
     * Sets the location code
     * @param locactionCode The location identifier to set
     */
    public void setLocactionCode(String locactionCode) {
        this.locactionCode = locactionCode;
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
    public Date getCreatedOn() {
        return createdOn;
    }
    
    /**
     * Sets the creation timestamp
     * @param createdOn When the department was created
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
    
    /**
     * Gets the last update timestamp
     * @return When the department was last updated
     */
    public Date getUpdatedOn() {
        return updatedOn;
    }
    
    /**
     * Sets the last update timestamp
     * @param updatedOn When the department was last updated
     */
    public void setUpdatedOn(Date updatedOn) {
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