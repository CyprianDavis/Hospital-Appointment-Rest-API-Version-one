package com.davis.hospital_Appointment_Rest_API.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davis.hospital_Appointment_Rest_API.model.Authority;
import com.davis.hospital_Appointment_Rest_API.repository.AuthorityRepository;
import com.davis.hospital_Appointment_Rest_API.service.AuthorityService;
/**
 * @author CYPRIAN DAVIS
 */
@Service
public class AuthorityServieImp implements AuthorityService {
	@Autowired
	private AuthorityRepository authorityRepository;

	@Override
	public List<Authority> findAll() {
		// TODO Auto-generated method stub
		return authorityRepository.findAll();
	}

	@Override
	public Authority save(Authority authority) {
		// TODO Auto-generated method stub
		return authorityRepository.save(authority);
	}

	@Override
	public Authority findByName(String name) {
		// TODO Auto-generated method stub
		return authorityRepository.findByNameIgnoreCase(name);
	}

}
