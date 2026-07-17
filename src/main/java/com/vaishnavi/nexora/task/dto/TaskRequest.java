package com.vaishnavi.nexora.task.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;


public class TaskRequest {


    @NotBlank(message = "Task title is required")
    private String title;



    private String description;



    @Pattern(
            regexp = "PENDING|IN_PROGRESS|COMPLETED",
            message = "Status must be PENDING, IN_PROGRESS or COMPLETED"
    )
    private String status;



    @Pattern(
            regexp = "LOW|MEDIUM|HIGH",
            message = "Priority must be LOW, MEDIUM or HIGH"
    )
    private String priority;



    private LocalDate dueDate;



    public TaskRequest() {
    }



    public TaskRequest(
            String title,
            String description,
            String status,
            String priority,
            LocalDate dueDate
    ) {

        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;

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



    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }



    public String getPriority() {
        return priority;
    }


    public void setPriority(String priority) {
        this.priority = priority;
    }



    public LocalDate getDueDate() {
        return dueDate;
    }


    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

}