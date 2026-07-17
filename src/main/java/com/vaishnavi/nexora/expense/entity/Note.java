package com.vaishnavi.nexora.expense.entity;


import com.vaishnavi.nexora.category.entity.Category;
import com.vaishnavi.nexora.entity.User;

import jakarta.persistence.*;

import java.time.LocalDateTime;



@Entity
@Table(name = "notes")
public class Note {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(nullable = false)
    private String title;



    @Column(columnDefinition = "TEXT")
    private String content;



    // Note color
    @Column(nullable = false, length = 50)
    private String color = "WHITE";



    @Column(nullable = false)
    private LocalDateTime createdAt;



    private LocalDateTime updatedAt;



    // ================= STATUS =================


    @Column(nullable = false)
    private boolean pinned = false;



    @Column(nullable = false)
    private boolean archived = false;



    @Column(nullable = false)
    private boolean favorite = false;



    @Column(nullable = false)
    private boolean deleted = false;





    // ================= USER RELATION =================


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;





    // ================= CATEGORY RELATION =================


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;





    // ================= CONSTRUCTOR =================


    public Note() {

    }





    // ================= TIMESTAMP =================


    @PrePersist
    public void prePersist(){

        createdAt = LocalDateTime.now();

        updatedAt = LocalDateTime.now();

    }



    @PreUpdate
    public void preUpdate(){

        updatedAt = LocalDateTime.now();

    }





    // ================= GETTERS SETTERS =================


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



    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }



    public String getColor() {
        return color;
    }


    public void setColor(String color) {
        this.color = color;
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



    public boolean isPinned() {
        return pinned;
    }


    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }



    public boolean isArchived() {
        return archived;
    }


    public void setArchived(boolean archived) {
        this.archived = archived;
    }



    public boolean isFavorite() {
        return favorite;
    }


    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }



    public boolean isDeleted() {
        return deleted;
    }


    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }



    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }



    public Category getCategory() {
        return category;
    }


    public void setCategory(Category category) {
        this.category = category;
    }

}