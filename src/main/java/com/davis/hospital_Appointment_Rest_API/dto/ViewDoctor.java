package com.davis.hospital_Appointment_Rest_API.dto;

public class ViewDoctor {
	private String userId;
	private String surName;
	private String givenName;
	private String otherName;
	private String specialization;
	private String license_number;
	private double consulation_fee;
	private String department;
	private String email;
	/**
	 * @param userId
	 * @param surName
	 * @param givenName
	 * @param otherName
	 * @param specialization
	 * @param license_number
	 * @param consulation_fee
	 * @param department
	 * @param email
	 */
	public ViewDoctor(String userId, String surName, String givenName, String otherName, String specialization,
			String license_number, double consulation_fee, String department, String email) {
		this.userId = userId;
		this.surName = surName;
		this.givenName = givenName;
		this.otherName = otherName;
		this.specialization = specialization;
		this.license_number = license_number;
		this.consulation_fee = consulation_fee;
		this.department = department;
		this.email = email;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the surName
	 */
	public String getSurName() {
		return surName;
	}
	/**
	 * @param surName the surName to set
	 */
	public void setSurName(String surName) {
		this.surName = surName;
	}
	/**
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}
	/**
	 * @param givenName the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	/**
	 * @return the otherName
	 */
	public String getOtherName() {
		return otherName;
	}
	/**
	 * @param otherName the otherName to set
	 */
	public void setOtherName(String otherName) {
		this.otherName = otherName;
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
	/**
	 * @return the license_number
	 */
	public String getLicense_number() {
		return license_number;
	}
	/**
	 * @param license_number the license_number to set
	 */
	public void setLicense_number(String license_number) {
		this.license_number = license_number;
	}
	/**
	 * @return the consulation_fee
	 */
	public double getConsulation_fee() {
		return consulation_fee;
	}
	/**
	 * @param consulation_fee the consulation_fee to set
	 */
	public void setConsulation_fee(double consulation_fee) {
		this.consulation_fee = consulation_fee;
	}
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	 
	    

}
