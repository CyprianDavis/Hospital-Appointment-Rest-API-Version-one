package com.davis.hospital_Appointment_Rest_API.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davis.hospital_Appointment_Rest_API.model.Role;
import com.davis.hospital_Appointment_Rest_API.repository.RoleRepository;
import com.davis.hospital_Appointment_Rest_API.service.RoleService;

/**
 * @author CYPRIAN DAVIS
 */
@Service
public class RoleServiceImp implements RoleService {
	@Autowired
	private RoleRepository roleRepository;
	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}

	@Override
	public Role save(Role role) {
		// TODO Auto-generated method stub
		return roleRepository.save(role);
	}

	@Override
	public Role findByName(String name) {
		// TODO Auto-generated method stub
		return roleRepository.findByNameIgnoreCase(name);
	}

}
