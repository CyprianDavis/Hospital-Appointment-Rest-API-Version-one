package com.davis.hospital_Appointment_Rest_API.model;

import java.time.LocalTime;

import jakarta.persistence.Embeddable;

/**
 * Represents a break period in a doctor's schedule.
 * This embeddable class can be included in DoctorSchedule entity.
 */
@Embeddable
public class DoctorBreaks {
    
    private String breakType;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Default constructor
     */
    public DoctorBreaks() {
    }

    /**
     * Constructs a DoctorBreaks with all fields
     * @param breakType The type of break (e.g., "Lunch", "Meeting")
     * @param startTime The start time of the break
     * @param endTime The end time of the break
     */
    public DoctorBreaks(String breakType, LocalTime startTime, LocalTime endTime) {
        this.breakType = breakType;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * @return the breakType
     */
    public String getBreakType() {
        return breakType;
    }

    /**
     * @param breakType the breakType to set
     */
    public void setBreakType(String breakType) {
        this.breakType = breakType;
    }

    /**
     * @return the startTime
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

  
}