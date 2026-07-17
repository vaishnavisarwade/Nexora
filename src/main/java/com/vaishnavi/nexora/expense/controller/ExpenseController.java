package com.vaishnavi.nexora.expense.controller;


import com.vaishnavi.nexora.expense.dto.ExpenseRequest;
import com.vaishnavi.nexora.expense.dto.ExpenseResponse;
import com.vaishnavi.nexora.expense.service.ExpenseService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/expenses")
public class ExpenseController {



    @Autowired
    private ExpenseService expenseService;







    // ================= CREATE EXPENSE =================

    @PostMapping
    public ExpenseResponse createExpense(

            Authentication authentication,

            @Valid @RequestBody ExpenseRequest request

    ){

        return expenseService.createExpense(
                authentication.getName(),
                request
        );

    }








    // ================= GET ALL EXPENSES =================
    //
    // Example:
    // /expenses?page=0&size=10&sortBy=expenseDate&direction=desc


    @GetMapping
    public Page<ExpenseResponse> getMyExpenses(

            Authentication authentication,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size,

            @RequestParam(defaultValue = "expenseDate") String sortBy,

            @RequestParam(defaultValue = "desc") String direction

    ){

        return expenseService.getMyExpenses(
                authentication.getName(),
                page,
                size,
                sortBy,
                direction
        );

    }







    // ================= GET EXPENSE BY ID =================


    @GetMapping("/{id}")
    public ExpenseResponse getExpenseById(

            Authentication authentication,

            @PathVariable Long id

    ){

        return expenseService.getExpenseById(
                id,
                authentication.getName()
        );

    }








    // ================= UPDATE EXPENSE =================


    @PutMapping("/{id}")
    public ExpenseResponse updateExpense(

            Authentication authentication,

            @PathVariable Long id,

            @Valid @RequestBody ExpenseRequest request

    ){

        return expenseService.updateExpense(
                id,
                authentication.getName(),
                request
        );

    }








    // ================= DELETE EXPENSE =================


    @DeleteMapping("/{id}")
    public String deleteExpense(

            Authentication authentication,

            @PathVariable Long id

    ){


        expenseService.deleteExpense(
                id,
                authentication.getName()
        );


        return "Expense deleted successfully";

    }








    // ================= SEARCH EXPENSE =================
    //
    // Example:
    // /expenses/search?keyword=food&page=0&size=10


    @GetMapping("/search")
    public Page<ExpenseResponse> searchExpense(

            Authentication authentication,

            @RequestParam String keyword,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size

    ){


        return expenseService.searchExpense(
                authentication.getName(),
                keyword,
                page,
                size
        );

    }








    // ================= CATEGORY FILTER =================
    //
    // Example:
    // /expenses/category/FOOD


    @GetMapping("/category/{category}")
    public Page<ExpenseResponse> getByCategory(

            Authentication authentication,

            @PathVariable String category,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size

    ){


        return expenseService.getByCategory(
                authentication.getName(),
                category,
                page,
                size
        );

    }


}