package com.davis.hospital_Appointment_Rest_API.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davis.hospital_Appointment_Rest_API.model.Billing;
import com.davis.hospital_Appointment_Rest_API.repository.BillingRepository;
import com.davis.hospital_Appointment_Rest_API.service.BillingService;
@Service
public class BillingServiceImp implements BillingService {
	@Autowired
	private BillingRepository billingRepository;
	@Override
	public List<Billing> findAll() {
		// TODO Auto-generated method stub
		return billingRepository.findAll();
	}

	@Override
	public Billing save(Billing billing) {
		// TODO Auto-generated method stub
		billing.setPaymentStatus("PENDING");
		return billingRepository.save(billing);
	}

	@Override
	public List<Billing> searchByPatientName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
