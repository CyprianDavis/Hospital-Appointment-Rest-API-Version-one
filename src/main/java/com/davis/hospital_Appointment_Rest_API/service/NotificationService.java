package com.davis.hospital_Appointment_Rest_API.service;

import java.util.Optional;
import org.springframework.data.repository.query.Param;
import com.davis.hospital_Appointment_Rest_API.model.Notification;

/**
 * Service interface for managing {@link Notification} entities.
 * Extends the generic {@link Service} interface with notification-specific operations.
 * 
 * <p>Provides methods for managing system notifications including appointment reminders,
 * alerts, and other important messages for users.</p>
 *
 * @author CYPRIAN DAVIS
 * @since 1.0
 * @see Service
 * @see Notification
 */
public interface NotificationService extends Service<Notification> {

    /**
     * Finds a notification by the associated username.
     * 
     * <p>This method performs a case-sensitive search for notifications
     * linked to the specified username.</p>
     *
     * @param userName the username to search for (must not be null or empty)
     * @return an {@link Optional} containing the notification if found,
     *         or empty Optional if no notification exists for the given username
     * @throws IllegalArgumentException if userName is null or empty
     * @see Optional
     */
    Optional<Notification> findByUserName(@Param("userName") String userName);
}