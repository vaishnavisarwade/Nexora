package com.vaishnavi.nexora.notification.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class NotificationRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Message is required")
    private String message;

    private String type;

    private LocalDateTime scheduledAt;


    public NotificationRequest() {
    }


    public NotificationRequest(
            String title,
            String message,
            String type,
            LocalDateTime scheduledAt
    ) {
        this.title = title;
        this.message = message;
        this.type = type;
        this.scheduledAt = scheduledAt;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }


    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

}