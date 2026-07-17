package com.vaishnavi.nexora.health.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DietRequest {

    @NotBlank(message = "Meal type is required")
    private String mealType;

    @NotBlank(message = "Food name is required")
    private String foodName;

    private String quantity;

    @NotNull(message = "Calories are required")
    private Integer calories;

    private Double protein;

    private Double carbs;

    private Double fats;

    public DietRequest() {
    }

    public DietRequest(String mealType,
                       String foodName,
                       String quantity,
                       Integer calories,
                       Double protein,
                       Double carbs,
                       Double fats) {
        this.mealType = mealType;
        this.foodName = foodName;
        this.quantity = quantity;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }

    public Double getFats() {
        return fats;
    }

    public void setFats(Double fats) {
        this.fats = fats;
    }
}