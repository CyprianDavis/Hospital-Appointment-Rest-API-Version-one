package com.davis.hospital_Appointment_Rest_API.service.imp;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.davis.hospital_Appointment_Rest_API.config.IdGeneration;
import com.davis.hospital_Appointment_Rest_API.exceptions.UsernameNotFoundException;
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
    
    @Autowired
    private IdGeneration idGeneration;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
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
            user.getUserName(), user.getPassWord(), authorities);
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
        //Generate User Id
        int year = Year.now().getValue();
        long auto_id = idGeneration.getNextIdNumber("userId");
        user.setUserId(getUserId(auto_id, year));
        //Set creation date and Status
        
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedOn(now);
        user.setStatus("ACTIVE");
        
        //Hash User password
        String hashPwdString = passwordEncoder.encode(user.getPassWord());
        user.setPassWord(hashPwdString);
        
        
        return userRepository.save(user);
    }

    /**
     * Generates a unique User ID based on the given ID and year.
     *
     * @param id    The unique ID number.
     * @param year  The current year.
     * @return A formatted User ID.
     */
    private String getUserId(long id, int year) {
        if (id <= 9) {
            return "U0000" + id + "" + year;
        } else if (id >= 10 && id <= 99) {
            return "U000" + id + "" + year;
        } else if (id >= 100 && id <= 999) {
            return "U00" + id + "" + year;
        } else if (id >= 1000 && id <= 9999) {
            return "U0" + id + "" + year;
        } else {
            return "U" + id + "" + year;
        }
    }
}