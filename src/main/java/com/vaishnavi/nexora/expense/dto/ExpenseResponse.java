package com.vaishnavi.nexora.expense.dto;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;



public class ExpenseResponse {



    private Long id;


    private String title;


    private BigDecimal amount;


    private String category;


    private LocalDate expenseDate;


    private String paymentMethod;


    private String description;


    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;







    public ExpenseResponse(){

    }







    public ExpenseResponse(
            Long id,
            String title,
            BigDecimal amount,
            String category,
            LocalDate expenseDate,
            String paymentMethod,
            String description,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ){

        this.id = id;
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.expenseDate = expenseDate;
        this.paymentMethod = paymentMethod;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }







    public Long getId(){

        return id;

    }


    public void setId(Long id){

        this.id=id;

    }







    public String getTitle(){

        return title;

    }


    public void setTitle(String title){

        this.title=title;

    }







    public BigDecimal getAmount(){

        return amount;

    }


    public void setAmount(BigDecimal amount){

        this.amount=amount;

    }







    public String getCategory(){

        return category;

    }


    public void setCategory(String category){

        this.category=category;

    }







    public LocalDate getExpenseDate(){

        return expenseDate;

    }


    public void setExpenseDate(LocalDate expenseDate){

        this.expenseDate=expenseDate;

    }







    public String getPaymentMethod(){

        return paymentMethod;

    }


    public void setPaymentMethod(String paymentMethod){

        this.paymentMethod=paymentMethod;

    }







    public String getDescription(){

        return description;

    }


    public void setDescription(String description){

        this.description=description;

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