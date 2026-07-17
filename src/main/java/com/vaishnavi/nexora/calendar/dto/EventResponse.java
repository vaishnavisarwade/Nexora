package com.vaishnavi.nexora.calendar.dto;


import java.time.LocalDateTime;



public class EventResponse {



    private Long id;



    private String title;



    private String description;



    private LocalDateTime eventDate;



    private String location;



    private boolean completed;



    private LocalDateTime createdAt;



    private LocalDateTime updatedAt;







    public EventResponse(){

    }







    public EventResponse(
            Long id,
            String title,
            String description,
            LocalDateTime eventDate,
            String location,
            boolean completed,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ){

        this.id = id;
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.location = location;
        this.completed = completed;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }







    public Long getId(){
        return id;
    }


    public void setId(Long id){
        this.id=id;
    }







    public String getTitle(){
        return title;
    }


    public void setTitle(String title){
        this.title=title;
    }







    public String getDescription(){
        return description;
    }


    public void setDescription(String description){
        this.description=description;
    }







    public LocalDateTime getEventDate(){
        return eventDate;
    }


    public void setEventDate(LocalDateTime eventDate){
        this.eventDate=eventDate;
    }







    public String getLocation(){
        return location;
    }


    public void setLocation(String location){
        this.location=location;
    }







    public boolean isCompleted(){
        return completed;
    }


    public void setCompleted(boolean completed){
        this.completed=completed;
    }







    public LocalDateTime getCreatedAt(){
        return createdAt;
    }


    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt=createdAt;
    }







    public LocalDateTime getUpdatedAt(){
        return updatedAt;
    }


    public void setUpdatedAt(LocalDateTime updatedAt){
        this.updatedAt=updatedAt;
    }


}