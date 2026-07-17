package com.vaishnavi.nexora.calendar.entity;


import com.vaishnavi.nexora.entity.User;

import jakarta.persistence.*;

import java.time.LocalDateTime;



@Entity
@Table(name = "events")
public class Event {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(nullable = false)
    private String title;



    @Column(columnDefinition = "TEXT")
    private String description;



    private LocalDateTime eventDate;



    private String location;



    private boolean completed = false;



    private LocalDateTime createdAt;



    private LocalDateTime updatedAt;







    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;







    public Event(){

    }







    public Event(
            String title,
            String description,
            LocalDateTime eventDate,
            String location,
            User user
    ){

        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.location = location;
        this.user = user;

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







    public User getUser(){
        return user;
    }


    public void setUser(User user){
        this.user=user;
    }


}