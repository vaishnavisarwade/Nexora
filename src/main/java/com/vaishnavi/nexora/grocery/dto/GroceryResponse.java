package com.vaishnavi.nexora.grocery.dto;


import com.vaishnavi.nexora.grocery.entity.GroceryStatus;
import com.vaishnavi.nexora.grocery.entity.GroceryUnit;
import com.vaishnavi.nexora.grocery.entity.Priority;

import java.time.LocalDateTime;



public class GroceryResponse {



    private Long id;



    private String itemName;



    private Integer quantity;



    private GroceryUnit unit;



    private String category;



    private GroceryStatus status;



    private Priority priority;



    private Double estimatedPrice;



    private Double actualPrice;



    private LocalDateTime createdAt;



    private LocalDateTime updatedAt;







    public GroceryResponse(){

    }







    public GroceryResponse(
            Long id,
            String itemName,
            Integer quantity,
            GroceryUnit unit,
            String category,
            GroceryStatus status,
            Priority priority,
            Double estimatedPrice,
            Double actualPrice,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ){

        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
        this.unit = unit;
        this.category = category;
        this.status = status;
        this.priority = priority;
        this.estimatedPrice = estimatedPrice;
        this.actualPrice = actualPrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

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


}