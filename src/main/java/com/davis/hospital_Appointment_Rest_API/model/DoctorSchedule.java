package com.davis.hospital_Appointment_Rest_API.model;

import java.time.LocalTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a doctor's schedule in the Hospital Appointment System.
 * <p>
 * This entity maps a doctor's availability for appointments on specific days,
 * including their working hours and maximum appointment capacity.
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-06-03
 * @see Doctor
 */
@Entity
@Table(name = "Doctor_Schedule")
public class DoctorSchedule {
    
    /**
     * Unique identifier for the schedule entry
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * The doctor associated with this schedule
     */
    private Doctor doctor;
    
    /**
     * Day of the week for this schedule entry (e.g., "Monday", "Tuesday")
     */
    private String dayOfWeek;
    
    /**
     * The start time of the doctor's availability
     */
    private LocalTime startTime;
    
    /**
     * The end time of the doctor's availability
     */
    private LocalTime endTime;
    
    /**
     * Maximum number of appointments allowed in this time slot
     */
    private int maximumAppointments;

    /**
     * Constructs a new DoctorSchedule with the specified parameters.
     *
     * @param doctor The doctor associated with this schedule
     * @param dayOfWeek The day of the week for this schedule
     * @param startTime The start time of availability
     * @param endTime The end time of availability
     * @param maximumAppointments The maximum number of appointments allowed
     */
    public DoctorSchedule(Doctor doctor, String dayOfWeek, LocalTime startTime, LocalTime endTime,
            int maximumAppointments) {
        this.doctor = doctor;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maximumAppointments = maximumAppointments;
    }

    /**
     * Gets the unique identifier of this schedule entry.
     *
     * @return The schedule ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of this schedule entry.
     *
     * @param id The ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the doctor associated with this schedule.
     *
     * @return The doctor entity
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Sets the doctor associated with this schedule.
     *
     * @param doctor The doctor to associate
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * Gets the day of the week for this schedule.
     *
     * @return The day of week (e.g., "Monday")
     */
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * Sets the day of the week for this schedule.
     *
     * @param dayOfWeek The day of week to set
     */
    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * Gets the start time of availability.
     *
     * @return The start time
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time of availability.
     *
     * @param startTime The start time to set
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets the end time of availability.
     *
     * @return The end time
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time of availability.
     *
     * @param endTime The end time to set
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets the maximum number of appointments allowed.
     *
     * @return The maximum appointments
     */
    public int getMaximumAppointments() {
        return maximumAppointments;
    }

    /**
     * Sets the maximum number of appointments allowed.
     *
     * @param maximumAppointments The maximum appointments to set
     */
    public void setMaximumAppointments(int maximumAppointments) {
        this.maximumAppointments = maximumAppointments;
    }
}