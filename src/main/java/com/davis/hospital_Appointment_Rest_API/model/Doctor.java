package com.davis.hospital_Appointment_Rest_API.model;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;

/**
 * Represents a medical doctor entity in the Hospital Appointment System.
 * Extends the base {@link User} class with doctor-specific attributes and relationships.
 * Uses JOINED inheritance strategy with "DOCTOR" as the discriminator value.
 * 
 * <p>The entity maps to two database tables:
 * <ul>
 *   <li><b>users</b> - Contains common user fields inherited from User</li>
 *   <li><b>doctor</b> - Contains doctor-specific fields with userId as foreign key</li>
 * </ul>
 * </p>
 *
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-03
 * @see User
 * @see Department
 * @see DoctorSchedule
 */
@Entity
@DiscriminatorValue("DOCTOR")
@PrimaryKeyJoinColumn(name = "userId")
public class Doctor extends User {
    
    /** The doctor's surname or family name */
    private String surName;
    
    /** The doctor's given or first name */
    private String givenName;
    
    /** The doctor's middle name(s) (optional) */
    private String otherName;
    
    /** The doctor's medical specialization area (e.g., "Cardiology", "Pediatrics") */
    private String specialization;
    
    /** The doctor's official medical license number */
    private String license_number;
    
    /** The standard consultation fee in local currency units */
    private double consulation_fee;
    
    /** The department where the doctor practices */
    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;
    
    @OneToMany(mappedBy = "doctor")
    private Set<Prescription> prescriptions =new HashSet<>();
    /**
     * The set of medical records for patients treated by this doctor.
     * This represents the inverse side of the bidirectional relationship with {@link MedicalRecord}.
     * Each record in this collection references this doctor through its {@code doctor} field.
     * 
     * <p>The relationship is managed by the {@code doctor} field in the {@link MedicalRecord} entity,
     * which maintains the foreign key association in the database.</p>
     * 
     * <p>This collection should only contain records where this doctor was the primary treating physician.</p>
     * 
     * @see MedicalRecord#doctor
     */
    @OneToMany(mappedBy = "doctor")
    private Set<MedicalRecord> treatmentRecords;
    /**
     * The set of all prescriptions issued by this doctor to patients.
     * This is the inverse side of the bidirectional relationship with {@link Prescription}.
     * 
     * <p>Each prescription in this collection references this doctor through its {@code doctor} field, 
     * which maintains the foreign key relationship in the database.</p>
     * 
     * <p>This collection includes both active and historical prescriptions, 
     * providing a complete record of medications prescribed by this doctor.</p>
     * 
     * @see Prescription#doctor  // Ensure this matches the owning side field name
     */
    @OneToMany(mappedBy = "doctor")
    private Set<Prescription> prescribedMedications;
    
    
    /**
     * The collection of schedule entries defining this doctor's availability.
     * <p>
     * Represents a one-to-many relationship with {@link DoctorSchedule} where:
     * <ul>
     *   <li>One doctor can have multiple schedule entries</li>
     *   <li>The relationship is mapped by the "doctor" field in DoctorSchedule</li>
     *   <li>Uses Set to ensure unique schedule entries</li>
     * </ul>
     * </p>
     * 
     * <p><b>Usage Note:</b>
     * Changes to this collection will be cascaded according to JPA rules.
     * </p>
     * 
     * @see DoctorSchedule
     */
    @OneToMany(mappedBy = "doctor")
    private Set<DoctorSchedule> schedules;
    /**
     * The set of appointments scheduled with this doctor.
     * <p>
     * Represents a one-to-many relationship with the {@link Appointment} entity,
     * where the foreign key is maintained by the Appointment side (mapped by "doctor" field).
     * </p>
     * 
     * <p><b>Relationship Details:</b>
     * <ul>
     *   <li>One doctor can have many appointments</li>
     *   <li>Appointments are managed through this bidirectional relationship</li>
     *   <li>The Set implementation ensures unique appointments</li>
     * </ul>
     * </p>
     */
    @OneToMany(mappedBy = "doctor")
    private Set<Appointment> appointments;
    /**
     * Constructs a new Doctor instance with basic user information.
     * 
     * @param userName the unique username for authentication
     * @param passWord the encrypted password
     * @param contact the phone number in international format
     * @param district the user's district/locality
     * @param street the street address component
     * @param postalCode the postal code for mail
     */
    public Doctor(String userName, String passWord, String contact, String district, 
                 String street, String postalCode) {
        super(userName, passWord, contact, district, street, postalCode);
    }
    public Doctor() {}

    /**
     * Gets the doctor's surname.
     * @return the surname/family name
     */
    public String getSurName() {
        return surName;
    }

    /**
     * Sets the doctor's surname.
     * @param surName the surname/family name to set
     */
    public void setSurName(String surName) {
        this.surName = surName;
    }

    /**
     * Gets the doctor's given name.
     * @return the given/first name
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * Sets the doctor's given name.
     * @param givenName the given/first name to set
     */
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    /**
     * Gets the doctor's middle name(s).
     * @return the middle name(s), or null if not provided
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
     * @return the specialization area
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * Sets the doctor's medical specialization.
     * @param specialization the specialization to set
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    /**
     * Gets the doctor's license number.
     * @return the official license number
     */
    public String getLicense_number() {
        return license_number;
    }

    /**
     * Sets the doctor's license number.
     * @param license_number the official license number to set
     */
    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }

    /**
     * Gets the standard consultation fee.
     * @return the fee amount in local currency
     */
    public double getConsulation_fee() {
        return consulation_fee;
    }

    /**
     * Sets the standard consultation fee.
     * @param consulation_fee the fee amount to set (must be positive)
     */
    public void setConsulation_fee(double consulation_fee) {
        this.consulation_fee = consulation_fee;
    }

    /**
     * Gets the associated department.
     * @return the Department where the doctor practices
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Sets the associated department.
     * @param department the Department to assign
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * Gets the set of schedule entries for this doctor.
     * @return the Set of DoctorSchedule objects
     */
    public Set<DoctorSchedule> getSchedules() {
        return schedules;
    }

    /**
     * Sets the schedule entries for this doctor.
     * @param schedules the Set of DoctorSchedule objects to assign
     */
    public void setSchedules(Set<DoctorSchedule> schedules) {
        this.schedules = schedules;
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
	 * @return the treatmentRecords
	 */
	public Set<MedicalRecord> getTreatmentRecords() {
		return treatmentRecords;
	}

	/**
	 * @param treatmentRecords the treatmentRecords to set
	 */
	public void setTreatmentRecords(Set<MedicalRecord> treatmentRecords) {
		this.treatmentRecords = treatmentRecords;
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
	 * @return the prescribedMedications
	 */
	public Set<Prescription> getPrescribedMedications() {
		return prescribedMedications;
	}

	/**
	 * @param prescribedMedications the prescribedMedications to set
	 */
	public void setPrescribedMedications(Set<Prescription> prescribedMedications) {
		this.prescribedMedications = prescribedMedications;
	}
	
    
}