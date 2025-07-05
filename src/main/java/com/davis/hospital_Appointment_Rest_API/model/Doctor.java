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
    
    /**
     * The standard consultation fee charged by this doctor for patient visits.
     * <p>
     * Represented as a double to allow for decimal values. Measured in the system's
     * base currency (e.g., USD, UGX). Must be a non-negative value.
     * </p>
     */
    private double consulation_fee;
    
    /** 
     * The department where the doctor practices 
     * @see Department
     */
    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;
    
    /**
     * The collection of prescriptions issued by this doctor.
     * <p>
     * Represents a one-to-many relationship with {@link Prescription} where:
     * <ul>
     *   <li>One doctor can have many prescriptions</li>
     *   <li>The relationship is managed by the "doctor" field in Prescription</li>
     *   <li>Initialized as an empty HashSet to prevent null pointer exceptions</li>
     * </ul>
     * </p>
     */
    @OneToMany(mappedBy = "doctor")
    private Set<Prescription> prescriptions = new HashSet<>();
    
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
     * @see Prescription#doctor
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
     * 
     * @see Appointment
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
    
    /**
     * Default no-argument constructor required by JPA.
     * <p>
     * Initializes a new instance of the Doctor class with all fields set to null or default values.
     * </p>
     */
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
     * @throws IllegalArgumentException if surName is null or empty
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
     * @throws IllegalArgumentException if givenName is null or empty
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
     * @return the specialization area (e.g., "Cardiology", "Pediatrics")
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
     * @return the official license number in MED-XXXXXX format
     */
    public String getLicense_number() {
        return license_number;
    }

    /**
     * Sets the doctor's license number.
     * @param license_number the official license number to set (must follow MED-XXXXXX format)
     * @throws IllegalArgumentException if license number format is invalid
     */
    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }

    /**
     * Gets the associated department.
     * @return the Department where the doctor practices, or null if not assigned
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
     * @return the Set of DoctorSchedule objects, never null (empty if no schedules)
     */
    public Set<DoctorSchedule> getSchedules() {
        return schedules;
    }

    /**
     * Sets the schedule entries for this doctor.
     * @param schedules the Set of DoctorSchedule objects to assign
     * @throws IllegalArgumentException if schedules is null
     */
    public void setSchedules(Set<DoctorSchedule> schedules) {
        this.schedules = schedules;
    }

    /**
     * Gets the appointments scheduled with this doctor.
     * @return Set of Appointment objects, never null (empty if no appointments)
     */
    public Set<Appointment> getAppointmets() {
        return appointments;
    }

    /**
     * Sets the appointments for this doctor.
     * @param appointmets the Set of Appointment objects to assign
     * @throws IllegalArgumentException if appointmets is null
     */
    public void setAppointmets(Set<Appointment> appointmets) {
        this.appointments = appointmets;
    }

    /**
     * Gets the medical treatment records for patients treated by this doctor.
     * @return Set of MedicalRecord objects, never null (empty if no records)
     */
    public Set<MedicalRecord> getTreatmentRecords() {
        return treatmentRecords;
    }

    /**
     * Sets the medical treatment records for this doctor.
     * @param treatmentRecords the Set of MedicalRecord objects to assign
     * @throws IllegalArgumentException if treatmentRecords is null
     */
    public void setTreatmentRecords(Set<MedicalRecord> treatmentRecords) {
        this.treatmentRecords = treatmentRecords;
    }

    /**
     * Gets all appointments scheduled with this doctor.
     * @return Set of Appointment objects, never null (empty if no appointments)
     */
    public Set<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * Sets all appointments for this doctor.
     * @param appointments the Set of Appointment objects to assign
     * @throws IllegalArgumentException if appointments is null
     */
    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    /**
     * Gets all prescriptions issued by this doctor.
     * @return Set of Prescription objects, never null (empty if no prescriptions)
     */
    public Set<Prescription> getPrescriptions() {
        return prescriptions;
    }

    /**
     * Sets all prescriptions for this doctor.
     * @param prescriptions the Set of Prescription objects to assign
     * @throws IllegalArgumentException if prescriptions is null
     */
    public void setPrescriptions(Set<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    /**
     * Gets all prescribed medications issued by this doctor.
     * @return Set of Prescription objects, never null (empty if no prescriptions)
     * @see #getPrescriptions()  // This appears to be a duplicate method
     */
    public Set<Prescription> getPrescribedMedications() {
        return prescribedMedications;
    }

    /**
     * Sets all prescribed medications for this doctor.
     * @param prescribedMedications the Set of Prescription objects to assign
     * @throws IllegalArgumentException if prescribedMedications is null
     * @see #setPrescriptions(Set)  // This appears to be a duplicate method
     */
    public void setPrescribedMedications(Set<Prescription> prescribedMedications) {
        this.prescribedMedications = prescribedMedications;
    }
    
    /**
     * Gets the standard consultation fee for this doctor.
     * @return the consultation fee as a double value
     */
    public double getConsulation_fee() {
        return consulation_fee;
    }
    
    /**
     * Sets the standard consultation fee for this doctor.
     * @param consulation_fee the fee to set (must be non-negative)
     * @throws IllegalArgumentException if fee is negative
     */
    public void setConsulation_fee(double consulation_fee) {
        this.consulation_fee = consulation_fee;
    }
}