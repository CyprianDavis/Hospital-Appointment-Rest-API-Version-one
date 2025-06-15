package com.davis.hospital_Appointment_Rest_API.service;

import java.util.List;

/**
 * A generic service interface that defines common CRUD operations for entities.
 * 
 * @param <T> the type of entity this service will handle
 * @author CYPRIAN DAVIS
 */
public interface Service<T> {
   
    /**
     * Retrieves all entities of type T.
     * 
     * @return a list of all entities (empty list if no entities exist)
     */
    List<T> findAll();
    
    /**
     * Saves a given entity of type T.
     * 
     * @param t the entity to be saved
     * @return the saved entity
     */
    T save(T t);
}