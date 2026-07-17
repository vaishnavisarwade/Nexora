package com.vaishnavi.nexora.reminder.dto;


import java.time.LocalDateTime;


public class ReminderResponse {


    private Long id;

    private String title;

    private String description;

    private LocalDateTime reminderDate;

    private boolean completed;

    private LocalDateTime createdAt;



    public ReminderResponse() {

    }



    public ReminderResponse(
            Long id,
            String title,
            String description,
            LocalDateTime reminderDate,
            boolean completed,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.reminderDate = reminderDate;
        this.completed = completed;
        this.createdAt = createdAt;
    }



    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public LocalDateTime getReminderDate() {
        return reminderDate;
    }


    public void setReminderDate(LocalDateTime reminderDate) {
        this.reminderDate = reminderDate;
    }


    public boolean isCompleted() {
        return completed;
    }


    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}