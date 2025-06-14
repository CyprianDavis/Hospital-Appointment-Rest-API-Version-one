package com.davis.hospital_Appointment_Rest_API.service;

import java.util.List;

import com.davis.hospital_Appointment_Rest_API.model.Billing;
/**
 * @author CYPRIAN DAVIS
 */
public interface BillingService extends Service<Billing, Long>{
	List<Billing> searchByPatientName(String name);
	

}
