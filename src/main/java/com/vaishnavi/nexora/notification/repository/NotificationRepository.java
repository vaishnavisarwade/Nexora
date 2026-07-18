package com.vaishnavi.nexora.notification.repository;

import com.vaishnavi.nexora.entity.User;
import com.vaishnavi.nexora.notification.entity.Notification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository
        extends JpaRepository<Notification, Long> {


    // Get all notifications of a user
    Page<Notification> findByUser(
            User user,
            Pageable pageable
    );


    // Get unread notifications
    Page<Notification> findByUserAndReadFalse(
            User user,
            Pageable pageable
    );


    // Get read notifications
    Page<Notification> findByUserAndReadTrue(
            User user,
            Pageable pageable
    );


    // Search notifications by title
    Page<Notification>
    findByUserAndTitleContainingIgnoreCase(
            User user,
            String title,
            Pageable pageable
    );


    // Find notification belonging to a specific user
    java.util.Optional<Notification> findByIdAndUser(
            Long id,
            User user
    );


    // Find scheduled notifications that are ready
    List<Notification> findByScheduledAtLessThanEqualAndReadFalse(
            LocalDateTime currentTime
    );

}