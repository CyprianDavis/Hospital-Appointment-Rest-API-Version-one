package com.davis.hospital_Appointment_Rest_API.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.davis.hospital_Appointment_Rest_API.model.Notification;
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>{
	
	@Query("SELECT n FROM Notification n WHERE "
			+ "n.user.userName =:userName")
	Optional<Notification> findByUserName(@Param("userName")String userName);

}
