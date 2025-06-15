package com.davis.hospital_Appointment_Rest_API.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.davis.hospital_Appointment_Rest_API.model.User;

/**
 * Service interface for {@link User} entity operations.
 * Extends the generic {@link Service} interface with user-specific methods.
 * 
 * @author CYPRIAN DAVIS
 */
public interface UserService extends Service<User> {

    /**
     * Finds a user by their username or email address.
     * 
     * @param user the username or email address to search for
     * @return the matching user if found, or null if no user matches the criteria
     * @throws IllegalArgumentException if the provided user parameter is null or empty
     */
	UserDetails findByUserOrEmail(String user) throws UsernameNotFoundException;

	/**
	 * Updates the status of a user account.
	 * 
	 * @param userName the username of the account to update (must not be null or empty)
	 * @param status the new status to set for the user account (must not be null or empty)
	 * @return true if the status was successfully updated, false otherwise
	 * @throws IllegalArgumentException if the provided userName or status is null, empty, or invalid
	 * @throws EntityNotFoundException if the user account cannot be found
	 */
	boolean updateUserStatus(String userName, String status);
 
}