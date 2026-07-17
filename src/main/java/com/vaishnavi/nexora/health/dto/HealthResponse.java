package com.vaishnavi.nexora.health.dto;

import java.time.LocalDate;

public class HealthResponse {

    private Long id;

    private Double weight;

    private Double height;

    private Double bmi;

    private Double waterIntake;

    private Double sleepHours;

    private Integer steps;

    private Integer exerciseMinutes;

    private LocalDate recordDate;

    public HealthResponse() {
    }

    public HealthResponse(Long id,
                          Double weight,
                          Double height,
                          Double bmi,
                          Double waterIntake,
                          Double sleepHours,
                          Integer steps,
                          Integer exerciseMinutes,
                          LocalDate recordDate) {

        this.id = id;
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
        this.waterIntake = waterIntake;
        this.sleepHours = sleepHours;
        this.steps = steps;
        this.exerciseMinutes = exerciseMinutes;
        this.recordDate = recordDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getBmi() {
        return bmi;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
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

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }
}