package com.vaishnavi.nexora.notification.scheduler;

import com.vaishnavi.nexora.notification.entity.Notification;
import com.vaishnavi.nexora.notification.repository.NotificationRepository;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class NotificationScheduler {


    private final NotificationRepository notificationRepository;


    public NotificationScheduler(
            NotificationRepository notificationRepository
    ) {

        this.notificationRepository =
                notificationRepository;
    }


    // =====================================================
    // CHECK SCHEDULED NOTIFICATIONS
    // =====================================================

    @Scheduled(fixedRate = 60000)
    public void processScheduledNotifications() {


        LocalDateTime currentTime =
                LocalDateTime.now();


        List<Notification> notifications =
                notificationRepository
                        .findByScheduledAtLessThanEqualAndReadFalse(
                                currentTime
                        );


        for (Notification notification : notifications) {


            System.out.println(
                    "🔔 NOTIFICATION REMINDER"
            );


            System.out.println(
                    "User: "
                            + notification
                            .getUser()
                            .getEmail()
            );


            System.out.println(
                    "Title: "
                            + notification
                            .getTitle()
            );


            System.out.println(
                    "Message: "
                            + notification
                            .getMessage()
            );


            System.out.println(
                    "Scheduled At: "
                            + notification
                            .getScheduledAt()
            );


            /*
             * Mark as processed.
             *
             * Currently we mark it as read
             * after the scheduled time is reached.
             *
             * Later this can be replaced with:
             * - Email notification
             * - Push notification
             * - WebSocket notification
             * - AI reminder assistant
             */


            notification.setRead(true);


            notificationRepository.save(
                    notification
            );
        }
    }
}