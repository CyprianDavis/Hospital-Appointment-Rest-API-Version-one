package com.davis.hospital_Appointment_Rest_API.dto;

public class PatientDto {
	private String userName;
	private String surName;
	private String givenName;
	private String otherName;
	private String bloodGroup;
	private String contact;
	private String email;
	private String postalCode;
	/**
	 * @param userName
	 * @param surName
	 * @param givenName
	 * @param otherName
	 * @param bloodGroup
	 * @param contact
	 * @param email
	 * @param postalCode
	 */
	public PatientDto(String userName, String surName, String givenName, String otherName, String bloodGroup,
			String contact, String email, String postalCode) {
		this.userName = userName;
		this.surName = surName;
		this.givenName = givenName;
		this.otherName = otherName;
		this.bloodGroup = bloodGroup;
		this.contact = contact;
		this.email = email;
		this.postalCode = postalCode;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	 * @return the bloodGroup
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}
	/**
	 * @param bloodGroup the bloodGroup to set
	 */
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}
	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
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
	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	
	

	
}
