package com.davis.hospital_Appointment_Rest_API.service.imp;

import com.davis.hospital_Appointment_Rest_API.model.User;

public interface UserService extends Service<User, String>{
	
	User findByUserOrEmail(String user);
	

}
