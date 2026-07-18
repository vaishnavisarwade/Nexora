package com.vaishnavi.nexora.health.entity;

import com.vaishnavi.nexora.entity.User;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(
        name = "health_records",

        indexes = {

                // Frequently used for user's health records
                @Index(
                        name = "idx_health_user_id",
                        columnList = "user_id"
                ),

                // Date filtering and sorting
                @Index(
                        name = "idx_health_record_date",
                        columnList = "record_date"
                ),

                // Useful for health analytics
                @Index(
                        name = "idx_health_bmi",
                        columnList = "bmi"
                )
        }
)
public class HealthRecord {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private Double weight;


    @Column(nullable = false)
    private Double height;


    private Double bmi;


    private Double waterIntake;


    private Double sleepHours;


    private Integer steps;


    private Integer exerciseMinutes;


    private LocalDate recordDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;


    public HealthRecord() {

    }


    public HealthRecord(
            Long id,
            Double weight,
            Double height,
            Double bmi,
            Double waterIntake,
            Double sleepHours,
            Integer steps,
            Integer exerciseMinutes,
            LocalDate recordDate,
            User user
    ) {

        this.id = id;
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
        this.waterIntake = waterIntake;
        this.sleepHours = sleepHours;
        this.steps = steps;
        this.exerciseMinutes = exerciseMinutes;
        this.recordDate = recordDate;
        this.user = user;
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


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }

}