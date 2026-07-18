package com.vaishnavi.nexora.notification.entity;

import com.vaishnavi.nexora.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "notifications",

        indexes = {

                @Index(
                        name = "idx_notifications_user_id",
                        columnList = "user_id"
                ),

                @Index(
                        name = "idx_notifications_read",
                        columnList = "is_read"
                ),

                @Index(
                        name = "idx_notifications_created_at",
                        columnList = "created_at"
                ),

                @Index(
                        name = "idx_notifications_type",
                        columnList = "type"
                )
        }
)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String title;


    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;


    private String type;


    @Column(name = "is_read", nullable = false)
    private boolean read = false;


    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;


    private LocalDateTime scheduledAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;


    public Notification() {
    }


    public Notification(
            Long id,
            String title,
            String message,
            String type,
            boolean read,
            LocalDateTime createdAt,
            LocalDateTime scheduledAt,
            User user
    ) {

        this.id = id;
        this.title = title;
        this.message = message;
        this.type = type;
        this.read = read;
        this.createdAt = createdAt;
        this.scheduledAt = scheduledAt;
        this.user = user;
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


    public boolean isRead() {
        return read;
    }


    public void setRead(boolean read) {
        this.read = read;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }


    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }

}