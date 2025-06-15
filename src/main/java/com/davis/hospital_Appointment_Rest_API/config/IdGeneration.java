package com.davis.hospital_Appointment_Rest_API.config;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Component
public class IdGeneration {
	
	@PersistenceContext
    private EntityManager entityManager;
	@Transactional // Ensures the method runs within a transaction
	public long getNextIdNumber(String idName) {
		long currentValue = 0;
		try {
			// SQL query to get the current value for the sequence
			String selectSql = "SELECT idValue FROM ID_Gen WHERE idName = :idName";
			Query selectQuery = entityManager.createNativeQuery(selectSql);
			selectQuery.setParameter("idName", idName);
			currentValue = ((Number) selectQuery.getSingleResult()).intValue();
			
			// Increment the value
			long nextValue = currentValue + 1;
			
			// SQL query to update the sequence value
			String updateSql = "UPDATE ID_Gen SET idValue = :nextValue WHERE idName = :idName";
			Query updateQuery = entityManager.createNativeQuery(updateSql);
			updateQuery.setParameter("nextValue", nextValue);
			updateQuery.setParameter("idName", idName);
			
			int rowsUpdated = updateQuery.executeUpdate();
			if (rowsUpdated == 0) {
				throw new RuntimeException("No rows updated. Check if the 'user' record exists.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error updating sequence value", e);
		}
		return currentValue;
	}

}
