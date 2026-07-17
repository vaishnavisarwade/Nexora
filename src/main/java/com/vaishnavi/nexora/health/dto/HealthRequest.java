package com.vaishnavi.nexora.health.dto;

import jakarta.validation.constraints.NotNull;

public class HealthRequest {

    @NotNull(message = "Weight is required")
    private Double weight;

    @NotNull(message = "Height is required")
    private Double height;

    private Double waterIntake;

    private Double sleepHours;

    private Integer steps;

    private Integer exerciseMinutes;

    public HealthRequest() {
    }

    public HealthRequest(Double weight,
                         Double height,
                         Double waterIntake,
                         Double sleepHours,
                         Integer steps,
                         Integer exerciseMinutes) {
        this.weight = weight;
        this.height = height;
        this.waterIntake = waterIntake;
        this.sleepHours = sleepHours;
        this.steps = steps;
        this.exerciseMinutes = exerciseMinutes;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWaterIntake() {
        return waterIntake;
    }

    public void setWaterIntake(Double waterIntake) {
        this.waterIntake = waterIntake;
    }

    public Double getSleepHours() {
        return sleepHours;
    }

    public void setSleepHours(Double sleepHours) {
        this.sleepHours = sleepHours;
    }

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public Integer getExerciseMinutes() {
        return exerciseMinutes;
    }

    public void setExerciseMinutes(Integer exerciseMinutes) {
        this.exerciseMinutes = exerciseMinutes;
    }
}