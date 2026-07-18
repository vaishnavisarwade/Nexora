package com.vaishnavi.nexora.notification.controller;

import com.vaishnavi.nexora.common.dto.PageRequestDTO;
import com.vaishnavi.nexora.common.dto.PageResponse;
import com.vaishnavi.nexora.notification.dto.NotificationRequest;
import com.vaishnavi.nexora.notification.dto.NotificationResponse;
import com.vaishnavi.nexora.notification.service.NotificationService;

import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {


    @Autowired
    private NotificationService notificationService;


    // =====================================================
    // CREATE NOTIFICATION
    // =====================================================

    @Operation(
            summary = "Create notification"
    )
    @PostMapping
    public ResponseEntity<NotificationResponse>
    createNotification(

            @Valid
            @RequestBody
            NotificationRequest request,

            Authentication authentication
    ) {


        NotificationResponse response =
                notificationService.createNotification(
                        request,
                        authentication
                );


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    // =====================================================
    // GET ALL NOTIFICATIONS
    // =====================================================

    @Operation(
            summary = "Get notifications with pagination and search"
    )
    @GetMapping
    public PageResponse<NotificationResponse>
    getAllNotifications(

            @ModelAttribute
            PageRequestDTO request,

            Authentication authentication
    ) {


        return notificationService
                .getAllNotifications(
                        request,
                        authentication
                );
    }


    // =====================================================
    // GET UNREAD NOTIFICATIONS
    // =====================================================

    @Operation(
            summary = "Get unread notifications"
    )
    @GetMapping("/unread")
    public PageResponse<NotificationResponse>
    getUnreadNotifications(

            @ModelAttribute
            PageRequestDTO request,

            Authentication authentication
    ) {


        return notificationService
                .getUnreadNotifications(
                        request,
                        authentication
                );
    }


    // =====================================================
    // GET NOTIFICATION BY ID
    // =====================================================

    @Operation(
            summary = "Get notification by id"
    )
    @GetMapping("/{id}")
    public NotificationResponse
    getNotificationById(

            @PathVariable
            Long id,

            Authentication authentication
    ) {


        return notificationService
                .getNotificationById(
                        id,
                        authentication
                );
    }


    // =====================================================
    // MARK AS READ
    // =====================================================

    @Operation(
            summary = "Mark notification as read"
    )
    @PatchMapping("/{id}/read")
    public NotificationResponse
    markAsRead(

            @PathVariable
            Long id,

            Authentication authentication
    ) {


        return notificationService
                .markAsRead(
                        id,
                        authentication
                );
    }


    // =====================================================
    // MARK AS UNREAD
    // =====================================================

    @Operation(
            summary = "Mark notification as unread"
    )
    @PatchMapping("/{id}/unread")
    public NotificationResponse
    markAsUnread(

            @PathVariable
            Long id,

            Authentication authentication
    ) {


        return notificationService
                .markAsUnread(
                        id,
                        authentication
                );
    }


    // =====================================================
    // DELETE NOTIFICATION
    // =====================================================

    @Operation(
            summary = "Delete notification"
    )
    @DeleteMapping("/{id}")
    public String deleteNotification(

            @PathVariable
            Long id,

            Authentication authentication
    ) {


        notificationService
                .deleteNotification(
                        id,
                        authentication
                );


        return "Notification deleted successfully";
    }

}