package com.davis.hospital_Appointment_Rest_API.service;

import com.davis.hospital_Appointment_Rest_API.model.Authority;
/**
 * @author CYPRIAN DAVIS
 */

public interface AuthorityService  extends Service<String, Long>{
	
	Authority findByName(String name);

}
