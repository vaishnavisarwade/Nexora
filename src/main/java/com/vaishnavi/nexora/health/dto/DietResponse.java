package com.vaishnavi.nexora.health.dto;

import java.time.LocalDateTime;

public class DietResponse {

    private Long id;

    private String mealType;

    private String foodName;

    private String quantity;

    private Integer calories;

    private Double protein;

    private Double carbs;

    private Double fats;

    private LocalDateTime mealTime;

    public DietResponse() {
    }

    public DietResponse(Long id,
                        String mealType,
                        String foodName,
                        String quantity,
                        Integer calories,
                        Double protein,
                        Double carbs,
                        Double fats,
                        LocalDateTime mealTime) {
        this.id = id;
        this.mealType = mealType;
        this.foodName = foodName;
        this.quantity = quantity;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
        this.mealTime = mealTime;
    }

    public Long getId() {
        return id;
    }

    public String getMealType() {
        return mealType;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getQuantity() {
        return quantity;
    }

    public Integer getCalories() {
        return calories;
    }

    public Double getProtein() {
        return protein;
    }

    public Double getCarbs() {
        return carbs;
    }

    public Double getFats() {
        return fats;
    }

    public LocalDateTime getMealTime() {
        return mealTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }

    public void setFats(Double fats) {
        this.fats = fats;
    }

    public void setMealTime(LocalDateTime mealTime) {
        this.mealTime = mealTime;
    }
}