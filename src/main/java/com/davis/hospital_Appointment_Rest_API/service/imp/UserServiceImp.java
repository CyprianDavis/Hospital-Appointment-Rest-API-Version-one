package com.davis.hospital_Appointment_Rest_API.service.imp;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.davis.hospital_Appointment_Rest_API.model.User;
import com.davis.hospital_Appointment_Rest_API.service.UserService;
/**
 * @author CYPRIAN DAVIS
 */

public class UserServiceImp  implements UserDetailsService,UserService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(String i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByUserOrEmail(String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateUserStatus(String status) {
		// TODO Auto-generated method stub
		return false;
	}

}
