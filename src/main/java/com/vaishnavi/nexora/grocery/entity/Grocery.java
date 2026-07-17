package com.vaishnavi.nexora.grocery.entity;


import com.vaishnavi.nexora.entity.User;

import jakarta.persistence.*;

import java.time.LocalDateTime;



@Entity
@Table(name = "groceries")
public class Grocery {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(nullable = false)
    private String itemName;



    private Integer quantity;



    @Enumerated(EnumType.STRING)
    private GroceryUnit unit;



    private String category;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GroceryStatus status = GroceryStatus.PENDING;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority = Priority.MEDIUM;



    private Double estimatedPrice;



    private Double actualPrice;



    private LocalDateTime createdAt;



    private LocalDateTime updatedAt;





    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;







    public Grocery(){

    }







    public Grocery(
            String itemName,
            Integer quantity,
            GroceryUnit unit,
            String category,
            Priority priority,
            Double estimatedPrice,
            User user
    ){

        this.itemName = itemName;
        this.quantity = quantity;
        this.unit = unit;
        this.category = category;
        this.priority = priority;
        this.estimatedPrice = estimatedPrice;
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







    public String getItemName(){
        return itemName;
    }


    public void setItemName(String itemName){
        this.itemName=itemName;
    }







    public Integer getQuantity(){
        return quantity;
    }


    public void setQuantity(Integer quantity){
        this.quantity=quantity;
    }







    public GroceryUnit getUnit(){
        return unit;
    }


    public void setUnit(GroceryUnit unit){
        this.unit=unit;
    }







    public String getCategory(){
        return category;
    }


    public void setCategory(String category){
        this.category=category;
    }







    public GroceryStatus getStatus(){
        return status;
    }


    public void setStatus(GroceryStatus status){
        this.status=status;
    }







    public Priority getPriority(){
        return priority;
    }


    public void setPriority(Priority priority){
        this.priority=priority;
    }







    public Double getEstimatedPrice(){
        return estimatedPrice;
    }


    public void setEstimatedPrice(Double estimatedPrice){
        this.estimatedPrice=estimatedPrice;
    }







    public Double getActualPrice(){
        return actualPrice;
    }


    public void setActualPrice(Double actualPrice){
        this.actualPrice=actualPrice;
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