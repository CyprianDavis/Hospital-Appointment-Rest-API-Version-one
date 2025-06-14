package com.davis.hospital_Appointment_Rest_API.service;

import com.davis.hospital_Appointment_Rest_API.model.Role;
/**
 * @author CYPRIAN DAVIS
 */

public interface RoleService extends Service<Role, Long>{

	Role findByName(String name);
}
