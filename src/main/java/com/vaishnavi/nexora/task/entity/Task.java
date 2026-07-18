package com.vaishnavi.nexora.task.entity;

import com.vaishnavi.nexora.entity.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(
        name = "tasks",

        indexes = {

                // Frequently used for user's tasks
                @Index(
                        name = "idx_tasks_user_id",
                        columnList = "user_id"
                ),

                // Search optimization
                @Index(
                        name = "idx_tasks_title",
                        columnList = "title"
                ),

                // Status filtering
                @Index(
                        name = "idx_tasks_status",
                        columnList = "status"
                ),

                // Priority filtering
                @Index(
                        name = "idx_tasks_priority",
                        columnList = "priority"
                ),

                // Due date sorting and filtering
                @Index(
                        name = "idx_tasks_due_date",
                        columnList = "due_date"
                ),

                // Sorting by creation date
                @Index(
                        name = "idx_tasks_created_at",
                        columnList = "created_at"
                )
        }
)
public class Task {


    // =====================================================
    // PRIMARY KEY
    // =====================================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // =====================================================
    // TASK INFORMATION
    // =====================================================

    @Column(nullable = false)
    private String title;


    @Column(columnDefinition = "TEXT")
    private String description;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status = TaskStatus.PENDING;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskPriority priority = TaskPriority.MEDIUM;


    private LocalDate dueDate;


    // =====================================================
    // AI-RELATED TASK INFORMATION
    // =====================================================

    /**
     * Indicates whether this task is important.
     *
     * Example:
     * true  -> Exam preparation
     * false -> Buy stationery
     */
    @Column(nullable = false)
    private boolean important = false;


    /**
     * Indicates whether this task is urgent.
     *
     * Example:
     * true  -> Submit assignment today
     * false -> Learn a new topic
     */
    @Column(nullable = false)
    private boolean urgent = false;


    /**
     * Optional estimated duration of the task in minutes.
     *
     * Example:
     * 30  -> Quick task
     * 120 -> Large task
     */
    private Integer estimatedMinutes;


    // =====================================================
    // TIMESTAMPS
    // =====================================================

    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;


    // =====================================================
    // USER RELATIONSHIP
    // =====================================================

    /**
     * User who owns this task.
     */
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;


    // =====================================================
    // CONSTRUCTORS
    // =====================================================

    public Task() {
    }


    public Task(
            String title,
            String description,
            TaskStatus status,
            TaskPriority priority,
            LocalDate dueDate
    ) {

        this.title = title;

        this.description = description;

        this.status = status;

        this.priority = priority;

        this.dueDate = dueDate;
    }


    // =====================================================
    // JPA LIFECYCLE
    // =====================================================

    @PrePersist
    public void onCreate() {

        createdAt = LocalDateTime.now();

        updatedAt = LocalDateTime.now();
    }


    @PreUpdate
    public void onUpdate() {

        updatedAt = LocalDateTime.now();
    }


    // =====================================================
    // GETTERS AND SETTERS
    // =====================================================

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


    public TaskStatus getStatus() {
        return status;
    }


    public void setStatus(TaskStatus status) {
        this.status = status;
    }


    public TaskPriority getPriority() {
        return priority;
    }


    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }


    public LocalDate getDueDate() {
        return dueDate;
    }


    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }


    public boolean isImportant() {
        return important;
    }


    public void setImportant(boolean important) {
        this.important = important;
    }


    public boolean isUrgent() {
        return urgent;
    }


    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }


    public Integer getEstimatedMinutes() {
        return estimatedMinutes;
    }


    public void setEstimatedMinutes(Integer estimatedMinutes) {
        this.estimatedMinutes = estimatedMinutes;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }


    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }

}