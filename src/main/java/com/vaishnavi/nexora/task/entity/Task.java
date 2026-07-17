package com.vaishnavi.nexora.task.entity;


import com.vaishnavi.nexora.entity.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "tasks")
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String title;


    private String description;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status = TaskStatus.PENDING;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskPriority priority = TaskPriority.MEDIUM;



    private LocalDate dueDate;



    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;



    // User who owns this task
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



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



    @PrePersist
    public void onCreate(){

        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();

    }



    @PreUpdate
    public void onUpdate(){

        updatedAt = LocalDateTime.now();

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