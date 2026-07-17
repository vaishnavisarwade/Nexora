package com.vaishnavi.nexora.reminder.dto;


import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;


public class ReminderRequest {


    @NotBlank(message = "Title is required")
    private String title;


    private String description;


    private LocalDateTime reminderDate;



    public ReminderRequest() {

    }



    public ReminderRequest(
            String title,
            String description,
            LocalDateTime reminderDate
    ) {
        this.title = title;
        this.description = description;
        this.reminderDate = reminderDate;
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
}