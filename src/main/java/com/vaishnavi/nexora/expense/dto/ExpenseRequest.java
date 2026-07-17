package com.vaishnavi.nexora.expense.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;



public class ExpenseRequest {



    @NotBlank(message = "Title is required")
    private String title;



    @NotNull(message = "Amount is required")
    private BigDecimal amount;



    @NotBlank(message = "Category is required")
    private String category;



    @NotNull(message = "Expense date is required")
    private LocalDate expenseDate;



    private String paymentMethod;



    private String description;







    public ExpenseRequest(){

    }







    public ExpenseRequest(
            String title,
            BigDecimal amount,
            String category,
            LocalDate expenseDate,
            String paymentMethod,
            String description
    ){

        this.title = title;
        this.amount = amount;
        this.category = category;
        this.expenseDate = expenseDate;
        this.paymentMethod = paymentMethod;
        this.description = description;

    }







    public String getTitle(){

        return title;

    }


    public void setTitle(String title){

        this.title = title;

    }







    public BigDecimal getAmount(){

        return amount;

    }


    public void setAmount(BigDecimal amount){

        this.amount = amount;

    }







    public String getCategory(){

        return category;

    }


    public void setCategory(String category){

        this.category = category;

    }







    public LocalDate getExpenseDate(){

        return expenseDate;

    }


    public void setExpenseDate(LocalDate expenseDate){

        this.expenseDate = expenseDate;

    }







    public String getPaymentMethod(){

        return paymentMethod;

    }


    public void setPaymentMethod(String paymentMethod){

        this.paymentMethod = paymentMethod;

    }







    public String getDescription(){

        return description;

    }


    public void setDescription(String description){

        this.description = description;

    }


}