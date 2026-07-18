package com.vaishnavi.nexora.notification.dto;

import java.time.LocalDateTime;

public class NotificationResponse {

    private Long id;

    private String title;

    private String message;

    private String type;

    private boolean read;

    private LocalDateTime createdAt;

    private LocalDateTime scheduledAt;


    public NotificationResponse(
            Long id,
            String title,
            String message,
            String type,
            boolean read,
            LocalDateTime createdAt,
            LocalDateTime scheduledAt
    ) {

        this.id = id;
        this.title = title;
        this.message = message;
        this.type = type;
        this.read = read;
        this.createdAt = createdAt;
        this.scheduledAt = scheduledAt;
    }


    public Long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }


    public String getMessage() {
        return message;
    }


    public String getType() {
        return type;
    }


    public boolean isRead() {
        return read;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

}