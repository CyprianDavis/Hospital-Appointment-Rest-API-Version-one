package com.davis.hospital_Appointment_Rest_API.service.imp;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davis.hospital_Appointment_Rest_API.model.Notification;
import com.davis.hospital_Appointment_Rest_API.repository.NotificationRepository;
import com.davis.hospital_Appointment_Rest_API.service.NotificationService;
/**
 * @author CYPRIAN DAVIS
 */
@Service
public class NotificationServiceImp implements NotificationService {
	@Autowired
	private NotificationRepository notificationRepository;
	@Override
	public List<Notification> findAll() {
		// TODO Auto-generated method stub
		return notificationRepository.findAll();
	}

	@Override
	public Notification save(Notification notification) {
		// TODO Auto-generated method stub
		//Set Creation date 
		LocalDateTime now = LocalDateTime.now();
		notification.setCreatedOn(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));
		notification.setRead(false);
		return notificationRepository.save(notification);
	}

	@Override
	public Optional<Notification> findByUserName(String userName) {
		// TODO Auto-generated method stub
		return notificationRepository.findByUserName(userName);
	}

}
