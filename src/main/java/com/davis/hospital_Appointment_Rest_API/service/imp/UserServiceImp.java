package com.davis.hospital_Appointment_Rest_API.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.davis.hospital_Appointment_Rest_API.model.User;
import com.davis.hospital_Appointment_Rest_API.repository.UserRepository;
import com.davis.hospital_Appointment_Rest_API.service.UserService;
/**
 * @author CYPRIAN DAVIS
 */
@Service
public class UserServiceImp  implements UserDetailsService,UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByUserName(username).orElseThrow(() -> new
				UsernameNotFoundException("User details not found for the user:"+ username));
		List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().getName()));
		return new org.springframework.security.core.userdetails.User(username, username, authorities);
	}

	@Override
	public Optional<User> (String id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
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
