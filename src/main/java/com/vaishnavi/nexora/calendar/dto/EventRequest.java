package com.vaishnavi.nexora.calendar.dto;


import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;



public class EventRequest {



    @NotBlank(message = "Title is required")
    private String title;



    private String description;



    private LocalDateTime eventDate;



    private String location;



    private boolean completed = false;







    public EventRequest(){

    }







    public EventRequest(
            String title,
            String description,
            LocalDateTime eventDate,
            String location,
            boolean completed
    ){

        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.location = location;
        this.completed = completed;

    }







    public String getTitle(){
        return title;
    }


    public void setTitle(String title){
        this.title = title;
    }







    public String getDescription(){
        return description;
    }


    public void setDescription(String description){
        this.description = description;
    }







    public LocalDateTime getEventDate(){
        return eventDate;
    }


    public void setEventDate(LocalDateTime eventDate){
        this.eventDate = eventDate;
    }







    public String getLocation(){
        return location;
    }


    public void setLocation(String location){
        this.location = location;
    }







    public boolean isCompleted(){
        return completed;
    }


    public void setCompleted(boolean completed){
        this.completed = completed;
    }


}