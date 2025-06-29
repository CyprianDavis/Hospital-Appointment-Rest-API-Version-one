package com.davis.hospital_Appointment_Rest_API.service.imp;

import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.davis.hospital_Appointment_Rest_API.model.DoctorSchedule;
import com.davis.hospital_Appointment_Rest_API.repository.DoctorScheduleRepository;
import com.davis.hospital_Appointment_Rest_API.service.DoctorScheduleService;
/**
 * @author CYPRIAN DAVIS
 */
@Service
public class DoctorScheduleServiceImp  implements DoctorScheduleService{
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
		if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name parameter cannot be null or empty");
		}
		String[] terms = name.trim().split("\\s+");
		
		List<List<DoctorSchedule>> allMatches = Arrays.stream(terms)
				.map(doctorScheduleRepository::searchByDoctorName)
				.toList();
		// Find intersection of all result sets
        return allMatches.stream()
            .reduce((list1, list2) -> {
                Set<DoctorSchedule> set = new HashSet<>(list2);
                return list1.stream()
                    .filter(set::contains)
                    .toList();
            })
            .orElse(Collections.emptyList());
	}

	@Override
	public List<DoctorSchedule> findByDayOfWeek(String day) {
		// TODO Auto-generated method stub
		return doctorScheduleRepository.findByDayOfWeek(day);
	}

}
