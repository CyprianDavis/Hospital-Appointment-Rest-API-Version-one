package com.davis.hospital_Appointment_Rest_API.service.imp;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.davis.hospital_Appointment_Rest_API.model.Department;
import com.davis.hospital_Appointment_Rest_API.repository.DepartmentRepository;
import com.davis.hospital_Appointment_Rest_API.service.DepartmentService;

public class DepartmentServiceImp implements DepartmentService{
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return departmentRepository.findAll();
	}

	@Override
	public Department save(Department department) {
		// TODO Auto-generated method stub
		//Set Creation date and Status
		LocalDateTime now = LocalDateTime.now();
		department.setCreatedOn(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));
				
		return departmentRepository.save(department);
	}

	@Override
	public Optional<Department> findByName(String department) {
		// TODO Auto-generated method stub
		return departmentRepository.findByNameIgnoreCase(department);
	}

	@Override
	public Optional<Department> findByLocationCode(String code) {
		// TODO Auto-generated method stub
		return departmentRepository.findByLocactionCode(code);
	}

}
