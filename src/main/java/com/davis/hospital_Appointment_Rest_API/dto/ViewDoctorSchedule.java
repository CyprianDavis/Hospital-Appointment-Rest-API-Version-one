package com.davis.hospital_Appointment_Rest_API.dto;

import java.time.LocalTime;

/**
 * Data Transfer Object (DTO) representing a doctor's schedule for viewing purposes.
 * <p>
 * This class encapsulates schedule information including time slots, availability,
 * and confirmation status, designed specifically for API responses.
 * </p>
 * 
 * @author CYPRIAN DAVIS
 * @version 1.0
 * @since 2025-07-09
 */
public class ViewDoctorSchedule {
    
    /** The full name of the doctor associated with this schedule */
    private String doctorName;
    
    /** The day of the week for this schedule (e.g., "Monday", "Tuesday") */
    private String dayOfWeek;
    
    /** The starting  time of the schedule slot */
    private LocalTime startTime;
    
    /** The  time of the schedule slot */
    private LocalTime endTime;
    
    /** The number of available appointment slots during this time period */
    private int availableSlots;
    
    /** Flag indicating whether the schedule has been confirmed by the doctor */
    private boolean isConfirmed;
    private String specialization;

    /**
     * Constructs a new ViewDoctorSchedule with all required fields.
     *
     * @param doctorName     the full name of the doctor
     * @param dayOfWeek      the day of the week for the schedule
     * @param localTime      the starting date and time of the schedule
     * @param localTime2        the ending date and time of the schedule
     * @param availableSlots the number of available appointment slots
     * @param isConfirmed    whether the schedule is confirmed
     */
    public ViewDoctorSchedule(String doctorName,String specialization, String dayOfWeek, LocalTime starTime, 
                            LocalTime endTime, int availableSlots, boolean isConfirmed) {
        this.doctorName = doctorName;
        this.dayOfWeek = dayOfWeek;
        this.startTime = starTime;
        this.endTime = endTime;
        this.availableSlots = availableSlots;
        this.isConfirmed = isConfirmed;
        this.specialization = specialization;
    }

    /**
     * Gets the doctor's full name.
     *
     * @return the doctor's name
     */
    public String getDoctorName() {
        return doctorName;
    }

    /**
     * Sets the doctor's full name.
     *
     * @param doctorName the name to set
     */
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    /**
     * Gets the day of the week for this schedule.
     *
     * @return the day of week (e.g., "Monday")
     */
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * Sets the day of the week for this schedule.
     *
     * @param dayOfWeek the day of week to set
     */
    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * Gets the start time of the schedule slot.
     *
     * @return the start date and time
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time of the schedule slot.
     *
     * @param startTime the start date and time to set
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    
    /**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	/**
     * Gets the end time of the schedule slot.
     *
     * @return the end date and time
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time of the schedule slot.
     *
     * @param endTime the end date and time to set
     */

    /**
     * Gets the number of available appointment slots.
     *
     * @return the number of available slots
     */
    public int getAvailableSlots() {
        return availableSlots;
    }

    /**
     * Sets the number of available appointment slots.
     *
     * @param availableSlots the number of slots to set (must be positive)
     * @throws IllegalArgumentException if availableSlots is negative
     */
    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }

    /**
     * Checks if the schedule has been confirmed by the doctor.
     *
     * @return true if confirmed, false otherwise
     */
    public boolean isConfirmed() {
        return isConfirmed;
    }

    /**
     * Sets the confirmation status of the schedule.
     *
     * @param isConfirmed the confirmation status to set
     */
    public void setConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

	/**
	 * @return the specialization
	 */
	public String getSpecialization() {
		return specialization;
	}

	/**
	 * @param specialization the specialization to set
	 */
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
    
}