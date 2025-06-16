package com.davis.hospital_Appointment_Rest_API.service.imp;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.davis.hospital_Appointment_Rest_API.model.DoctorSchedule;
import com.davis.hospital_Appointment_Rest_API.repository.DoctorScheduleRepository;
import com.davis.hospital_Appointment_Rest_API.service.DoctorScheduleService;
import com.davis.hospital_Appointment_Rest_API.service.Service;
/**
 * @author CYPRIAN DAVIS
 */

public class DoctorScheduleServiceImp  implements DoctorScheduleService,Service<DoctorSchedule>{
	@Autowired
	private DoctorScheduleRepository doctorScheduleRepository;
	@Override
	public List<DoctorSchedule> findAll() {
		// TODO Auto-generated method stub
		return doctorScheduleRepository.findAll();
	}

	@Override
	public DoctorSchedule save(DoctorSchedule schedule) {
		// TODO Auto-generated method stub
		
		return doctorScheduleRepository.save(schedule);
	}

	@Override
	public List<DoctorSchedule> findByDoctorSpecialization(String specialization) {
		// TODO Auto-generated method stub
		return doctorScheduleRepository.findByDoctorSpecialization(specialization);
	}

	@Override
	public Page<DoctorSchedule> comprehensiveSearchPaginated(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DoctorSchedule> searchByDoctorName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DoctorSchedule> findByDayOfWeek(String day) {
		// TODO Auto-generated method stub
		return doctorScheduleRepository.findByDayOfWeek(day);
	}

}
