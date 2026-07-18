package com.vaishnavi.nexora.notification.service;

import com.vaishnavi.nexora.common.dto.PageRequestDTO;
import com.vaishnavi.nexora.common.dto.PageResponse;
import com.vaishnavi.nexora.common.service.PaginationService;
import com.vaishnavi.nexora.entity.User;
import com.vaishnavi.nexora.exception.ResourceNotFoundException;
import com.vaishnavi.nexora.notification.dto.NotificationRequest;
import com.vaishnavi.nexora.notification.dto.NotificationResponse;
import com.vaishnavi.nexora.notification.entity.Notification;
import com.vaishnavi.nexora.notification.repository.NotificationRepository;
import com.vaishnavi.nexora.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {


    @Autowired
    private NotificationRepository notificationRepository;


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PaginationService paginationService;


    // =====================================================
    // GET LOGGED-IN USER
    // =====================================================

    private User getLoggedInUser(
            Authentication authentication
    ) {

        return userRepository
                .findByEmail(authentication.getName())
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "User not found"
                        )
                );
    }


    // =====================================================
    // CREATE NOTIFICATION
    // =====================================================

    public NotificationResponse createNotification(
            NotificationRequest request,
            Authentication authentication
    ) {

        User user = getLoggedInUser(authentication);


        Notification notification =
                new Notification();


        notification.setTitle(
                request.getTitle()
        );


        notification.setMessage(
                request.getMessage()
        );


        notification.setType(
                request.getType()
        );


        notification.setRead(false);


        notification.setCreatedAt(
                LocalDateTime.now()
        );


        notification.setScheduledAt(
                request.getScheduledAt()
        );


        notification.setUser(user);


        Notification saved =
                notificationRepository.save(
                        notification
                );


        return mapToResponse(saved);
    }


    // =====================================================
    // GET ALL NOTIFICATIONS
    // =====================================================

    public PageResponse<NotificationResponse>
    getAllNotifications(
            PageRequestDTO request,
            Authentication authentication
    ) {

        User user =
                getLoggedInUser(authentication);


        Pageable pageable =
                paginationService.createPageable(
                        request
                );


        Page<Notification> page;


        if (request.getKeyword() != null
                && !request.getKeyword().isBlank()) {


            page =
                    notificationRepository
                            .findByUserAndTitleContainingIgnoreCase(
                                    user,
                                    request.getKeyword(),
                                    pageable
                            );

        } else {


            page =
                    notificationRepository
                            .findByUser(
                                    user,
                                    pageable
                            );
        }


        List<NotificationResponse> notifications =
                page.getContent()
                        .stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList());


        return new PageResponse<>(
                notifications,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }


    // =====================================================
    // GET UNREAD NOTIFICATIONS
    // =====================================================

    public PageResponse<NotificationResponse>
    getUnreadNotifications(
            PageRequestDTO request,
            Authentication authentication
    ) {

        User user =
                getLoggedInUser(authentication);


        Pageable pageable =
                paginationService.createPageable(
                        request
                );


        Page<Notification> page =
                notificationRepository
                        .findByUserAndReadFalse(
                                user,
                                pageable
                        );


        return createPageResponse(page);
    }


    // =====================================================
    // GET NOTIFICATION BY ID
    // =====================================================

    public NotificationResponse getNotificationById(
            Long id,
            Authentication authentication
    ) {

        Notification notification =
                getNotificationEntity(
                        id,
                        authentication
                );


        return mapToResponse(notification);
    }


    // =====================================================
    // MARK AS READ
    // =====================================================

    public NotificationResponse markAsRead(
            Long id,
            Authentication authentication
    ) {

        Notification notification =
                getNotificationEntity(
                        id,
                        authentication
                );


        notification.setRead(true);


        Notification updated =
                notificationRepository.save(
                        notification
                );


        return mapToResponse(updated);
    }


    // =====================================================
    // MARK AS UNREAD
    // =====================================================

    public NotificationResponse markAsUnread(
            Long id,
            Authentication authentication
    ) {

        Notification notification =
                getNotificationEntity(
                        id,
                        authentication
                );


        notification.setRead(false);


        Notification updated =
                notificationRepository.save(
                        notification
                );


        return mapToResponse(updated);
    }


    // =====================================================
    // DELETE NOTIFICATION
    // =====================================================

    public void deleteNotification(
            Long id,
            Authentication authentication
    ) {

        Notification notification =
                getNotificationEntity(
                        id,
                        authentication
                );


        notificationRepository.delete(
                notification
        );
    }


    // =====================================================
    // GET NOTIFICATION ENTITY
    // =====================================================

    public Notification getNotificationEntity(
            Long id,
            Authentication authentication
    ) {

        User user =
                getLoggedInUser(
                        authentication
                );


        return notificationRepository
                .findByIdAndUser(id, user)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Notification not found"
                        )
                );
    }


    // =====================================================
    // COMMON PAGE RESPONSE
    // =====================================================

    private PageResponse<NotificationResponse>
    createPageResponse(
            Page<Notification> page
    ) {

        List<NotificationResponse> notifications =
                page.getContent()
                        .stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList());


        return new PageResponse<>(
                notifications,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }


    // =====================================================
    // ENTITY TO RESPONSE
    // =====================================================

    private NotificationResponse mapToResponse(
            Notification notification
    ) {

        return new NotificationResponse(

                notification.getId(),

                notification.getTitle(),

                notification.getMessage(),

                notification.getType(),

                notification.isRead(),

                notification.getCreatedAt(),

                notification.getScheduledAt()
        );
    }

}