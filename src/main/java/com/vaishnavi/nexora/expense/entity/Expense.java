package com.vaishnavi.nexora.expense.entity;

import com.vaishnavi.nexora.entity.User;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(
        name = "expenses",

        indexes = {

                // Frequently used for user's expenses
                @Index(
                        name = "idx_expenses_user_id",
                        columnList = "user_id"
                ),

                // Category filtering
                @Index(
                        name = "idx_expenses_category",
                        columnList = "category"
                ),

                // Date sorting and filtering
                @Index(
                        name = "idx_expenses_expense_date",
                        columnList = "expense_date"
                ),

                // Payment method filtering
                @Index(
                        name = "idx_expenses_payment_method",
                        columnList = "payment_method"
                ),

                // Sorting by creation date
                @Index(
                        name = "idx_expenses_created_at",
                        columnList = "created_at"
                )
        }
)
public class Expense {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String title;


    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;


    @Column(nullable = false)
    private String category;


    @Column(nullable = false)
    private LocalDate expenseDate;


    private String paymentMethod;


    @Column(columnDefinition = "TEXT")
    private String description;


    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;


    // USER RELATION

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;


    public Expense() {

    }


    public Expense(
            String title,
            BigDecimal amount,
            String category,
            LocalDate expenseDate,
            String paymentMethod,
            String description
    ) {

        this.title = title;
        this.amount = amount;
        this.category = category;
        this.expenseDate = expenseDate;
        this.paymentMethod = paymentMethod;
        this.description = description;
    }


    @PrePersist
    public void onCreate() {

        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }


    @PreUpdate
    public void onUpdate() {

        updatedAt = LocalDateTime.now();
    }


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


    public BigDecimal getAmount() {
        return amount;
    }


    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }


    public LocalDate getExpenseDate() {
        return expenseDate;
    }


    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }


    public String getPaymentMethod() {
        return paymentMethod;
    }


    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
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


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }

}