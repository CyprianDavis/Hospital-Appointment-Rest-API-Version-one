package com.davis.hospital_Appointment_Rest_API.service.imp;

import java.util.List;

/**
 * A generic service interface that defines common CRUD operations for entities.
 * 
 * @param <T> the type of entity this service will handle
 * @param <I> the type of the entity's identifier (typically Long or String)
 * @author CYPRIAN DAVIS
 */
public interface Service<T,I> {
    /**
     * Retrieves an entity by its unique identifier.
     * 
     * @param i the identifier of the entity to be retrieved
     * @return the entity with the given identifier or null if not found
     */
    T findById(I i);
    
    /**
     * Retrieves all entities of type T.
     * 
     * @return a list of all entities (empty list if no entities exist)
     */
    List<T> findAll();
}