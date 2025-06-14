package com.davis.hospital_Appointment_Rest_API.service;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;

import com.davis.hospital_Appointment_Rest_API.model.DoctorSchedule;

/**
 * @author CYPRIAN DAVIS
 */

public interface DoctorScheduleService extends Service<DoctorSchedule, Long>{
	
	List<DoctorSchedule> findByDostorSpecializtion(String specialization);
	Page<DoctorSchedule> comprehensiveSearchPaginated(Pageable pageable);
	List<DoctorSchedule> searchByDoctorName(String name);
	List<DoctorSchedule> findByDayOfWeek(String day);
	
	
	

}
