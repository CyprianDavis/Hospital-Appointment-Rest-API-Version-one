package com.davis.hospital_Appointment_Rest_API.service.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.davis.hospital_Appointment_Rest_API.model.User;
import com.davis.hospital_Appointment_Rest_API.repository.UserRepository;
import com.davis.hospital_Appointment_Rest_API.service.UserService;

/**
 * Implementation of {@link UserService} and {@link UserDetailsService} that provides
 * user management and authentication services.
 * <p>
 * This service handles user operations including loading user details for authentication,
 * retrieving user information, and managing user account status.
 * </p>
 * 
 * @author CYPRIAN DAVIS
 */
@Service
public class UserServiceImp implements UserDetailsService, UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    /**
     * Loads user details by username for Spring Security authentication.
     *
     * @param username the username to search for (must not be null or empty)
     * @return UserDetails containing the user's authentication information
     * @throws UsernameNotFoundException if no user is found with the given username
     * @throws IllegalArgumentException if the username is null or empty
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
            .orElseThrow(() -> new UsernameNotFoundException(
                "User details not found for the user: " + username));
        
        List<GrantedAuthority> authorities = List.of(
            new SimpleGrantedAuthority(user.getRole().getName()));
        
        return new org.springframework.security.core.userdetails.User(
            username, username, authorities);
    }

    /**
     * Retrieves all users in the system.
     *
     * @return a list of all {@link User} entities
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Finds user details by either username or email address.
     *
     * @param nameOrEmail the username or email address to search for
     * @return UserDetails containing the user's authentication information
     * @throws UsernameNotFoundException if no user matches the provided username or email
     * @throws IllegalArgumentException if the input parameter is null or empty
     */
    @Override
    public UserDetails findByUserOrEmail(String nameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUserNameOrEmail(nameOrEmail)
            .orElseThrow(() -> new UsernameNotFoundException(
                "User details not found - incorrect username or email"));
        
        List<GrantedAuthority> authorities = List.of(
            new SimpleGrantedAuthority(user.getRole().getName()));

        return new org.springframework.security.core.userdetails.User(
            nameOrEmail, nameOrEmail, authorities);
    }

    /**
     * Updates the status of a user account.
     *
     * @param userName the username of the account to update (must not be null or empty)
     * @param status the new status to set (must not be null or empty)
     * @return true if the status was successfully updated, false if no user was found
     * @throws IllegalArgumentException if either parameter is null or empty
     */
    @Override
    public boolean updateUserStatus(String userName, String status) {
        if (userName == null || userName.isEmpty() || status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Username and status must not be null or empty");
        }
        return userRepository.updateUserStatus(userName, status) > 0;
    }

    /**
     * Saves a user entity to the database.
     *
     * @param user the user entity to be saved (must not be null)
     * @return the saved user entity
     * @throws IllegalArgumentException if the user parameter is null
     */
    @Override
    public User save(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User must not be null");
        }
        
        return userRepository.save(user);
    }
}