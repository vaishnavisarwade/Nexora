package com.vaishnavi.nexora.reminder.entity;

import com.vaishnavi.nexora.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(
        name = "reminders",

        indexes = {

                // Frequently used for user's reminders
                @Index(
                        name = "idx_reminders_user_id",
                        columnList = "user_id"
                ),

                // Search optimization
                @Index(
                        name = "idx_reminders_title",
                        columnList = "title"
                ),

                // Reminder date filtering and sorting
                @Index(
                        name = "idx_reminders_reminder_date",
                        columnList = "reminder_date"
                ),

                // Completed status filtering
                @Index(
                        name = "idx_reminders_completed",
                        columnList = "completed"
                ),

                // Sorting by creation date
                @Index(
                        name = "idx_reminders_created_at",
                        columnList = "created_at"
                )
        }
)
public class Reminder {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String title;


    private String description;


    private LocalDateTime reminderDate;


    private boolean completed = false;


    private LocalDateTime createdAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;


    public Reminder() {

    }


    public Reminder(
            String title,
            String description,
            LocalDateTime reminderDate,
            User user
    ) {

        this.title = title;
        this.description = description;
        this.reminderDate = reminderDate;
        this.user = user;
        this.createdAt = LocalDateTime.now();
    }


    @PrePersist
    public void onCreate() {

        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
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


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }

}