package com.vaishnavi.nexora.grocery.dto;


import com.vaishnavi.nexora.grocery.entity.GroceryStatus;
import com.vaishnavi.nexora.grocery.entity.GroceryUnit;
import com.vaishnavi.nexora.grocery.entity.Priority;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



public class GroceryRequest {



    @NotBlank(message = "Item name is required")
    private String itemName;



    @NotNull(message = "Quantity is required")
    private Integer quantity;



    private GroceryUnit unit;



    private String category;



    private GroceryStatus status;



    private Priority priority;



    private Double estimatedPrice;



    private Double actualPrice;







    public GroceryRequest(){

    }







    public GroceryRequest(
            String itemName,
            Integer quantity,
            GroceryUnit unit,
            String category,
            GroceryStatus status,
            Priority priority,
            Double estimatedPrice,
            Double actualPrice
    ){

        this.itemName=itemName;
        this.quantity=quantity;
        this.unit=unit;
        this.category=category;
        this.status=status;
        this.priority=priority;
        this.estimatedPrice=estimatedPrice;
        this.actualPrice=actualPrice;

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


}