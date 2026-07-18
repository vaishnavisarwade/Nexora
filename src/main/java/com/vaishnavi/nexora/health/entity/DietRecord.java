package com.vaishnavi.nexora.health.entity;

import com.vaishnavi.nexora.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(
        name = "diet_records",

        indexes = {

                // Frequently used for user's diet records
                @Index(
                        name = "idx_diet_user_id",
                        columnList = "user_id"
                ),

                // Meal type filtering
                @Index(
                        name = "idx_diet_meal_type",
                        columnList = "meal_type"
                ),

                // Food search
                @Index(
                        name = "idx_diet_food_name",
                        columnList = "food_name"
                ),

                // Date and time filtering
                @Index(
                        name = "idx_diet_meal_time",
                        columnList = "meal_time"
                ),

                // Calorie-based analytics
                @Index(
                        name = "idx_diet_calories",
                        columnList = "calories"
                )
        }
)
public class DietRecord {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String mealType;


    @Column(nullable = false)
    private String foodName;


    private String quantity;


    private Integer calories;


    private Double protein;


    private Double carbs;


    private Double fats;


    private LocalDateTime mealTime;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;


    public DietRecord() {

    }


    public DietRecord(
            Long id,
            String mealType,
            String foodName,
            String quantity,
            Integer calories,
            Double protein,
            Double carbs,
            Double fats,
            LocalDateTime mealTime,
            User user
    ) {

        this.id = id;
        this.mealType = mealType;
        this.foodName = foodName;
        this.quantity = quantity;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
        this.mealTime = mealTime;
        this.user = user;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
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


    public LocalDateTime getMealTime() {
        return mealTime;
    }


    public void setMealTime(LocalDateTime mealTime) {
        this.mealTime = mealTime;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }

}