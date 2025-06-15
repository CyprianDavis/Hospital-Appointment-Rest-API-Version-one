package com.davis.hospital_Appointment_Rest_API.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.davis.hospital_Appointment_Rest_API.model.User;

/**
 * Repository interface for {@link User} entities that provides CRUD operations
 * and custom query methods for user management.
 * 
 * @author CYPRIAN DAVIS
 * 
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Finds a user by their username or email address.
     * 
     * @param user the username or email address to search for
     * @return an {@link Optional} containing the matching user if found,
     *         or empty {@link Optional} if no user matches the criteria
     */
    @Query("SELECT u FROM User u WHERE u.userName = :user OR u.email = :user")
    Optional<User> findByUserNameOrEmail(@Param("user") String user);
    
    Optional<User> findByUserName(String userName);
    
    /**
     * Updates the status of a user identified by their username.
     * 
     * @param userName the username of the user to update
     * @param status the new status to set
     * @return the number of users updated (should be 0 or 1)
     */
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.status = :status WHERE u.userName = :userName")
    int updateUserStatus(@Param("userName") String userName, @Param("status") String status);
}