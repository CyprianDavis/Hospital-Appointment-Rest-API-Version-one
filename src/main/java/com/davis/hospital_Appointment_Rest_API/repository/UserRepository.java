package com.davis.hospital_Appointment_Rest_API.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.davis.hospital_Appointment_Rest_API.model.User;

/**
 * Repository interface for {@link User} entities that provides CRUD operations
 * and custom query methods for user management.
 * 
 * @author CYPRIAN DAVIS
 */
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Finds a user by their username or email address.
     * 
     * @param user the username or email address to search for
     * @return an {@link Optional} containing the matching user if found,
     *         or empty {@link Optional} if no user matches the criteria
     */
    @Query("SELECT u FROM User u WHERE u.username = :user OR u.email = :user")
    Optional<User> findByUserOrEmail(@Param("user") String user);
}