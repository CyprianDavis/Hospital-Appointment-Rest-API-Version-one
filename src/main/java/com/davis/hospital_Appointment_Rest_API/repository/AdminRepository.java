package com.davis.hospital_Appointment_Rest_API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davis.hospital_Appointment_Rest_API.model.Admin;
@Repository
public interface AdminRepository extends JpaRepository<Admin, String>{

}
