package com.davis.hospital_Appointment_Rest_API.model;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Doctor_Breaks")
public class DoctorBreaks {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private DoctorSchedule doctorSchedule;
	private String breakType;
	private LocalTime sartTime;
	private LocalTime endTime;
	/**
	 * @param id
	 * @param doctorSchedule
	 * @param breakType
	 * @param sartTime
	 * @param endTime
	 */
	public DoctorBreaks(Long id, DoctorSchedule doctorSchedule, String breakType, LocalTime sartTime,
			LocalTime endTime) {
		this.id = id;
		this.doctorSchedule = doctorSchedule;
		this.breakType = breakType;
		this.sartTime = sartTime;
		this.endTime = endTime;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the doctorSchedule
	 */
	public DoctorSchedule getDoctorSchedule() {
		return doctorSchedule;
	}
	/**
	 * @param doctorSchedule the doctorSchedule to set
	 */
	public void setDoctorSchedule(DoctorSchedule doctorSchedule) {
		this.doctorSchedule = doctorSchedule;
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
	 * @return the sartTime
	 */
	public LocalTime getSartTime() {
		return sartTime;
	}
	/**
	 * @param sartTime the sartTime to set
	 */
	public void setSartTime(LocalTime sartTime) {
		this.sartTime = sartTime;
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
