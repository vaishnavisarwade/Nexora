package com.vaishnavi.nexora.expense.service;


import com.vaishnavi.nexora.entity.User;
import com.vaishnavi.nexora.expense.dto.ExpenseRequest;
import com.vaishnavi.nexora.expense.dto.ExpenseResponse;
import com.vaishnavi.nexora.expense.entity.Expense;
import com.vaishnavi.nexora.expense.repository.ExpenseRepository;
import com.vaishnavi.nexora.exception.ResourceNotFoundException;
import com.vaishnavi.nexora.repository.UserRepository;


import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


@Service
public class ExpenseService {



    private final ExpenseRepository expenseRepository;

    private final UserRepository userRepository;



    public ExpenseService(
            ExpenseRepository expenseRepository,
            UserRepository userRepository
    ){

        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;

    }






    // ================= CREATE EXPENSE =================


    public ExpenseResponse createExpense(
            String email,
            ExpenseRequest request
    ){


        User user = getUser(email);



        Expense expense = new Expense();


        expense.setTitle(request.getTitle());

        expense.setAmount(request.getAmount());

        expense.setCategory(request.getCategory());

        expense.setExpenseDate(request.getExpenseDate());

        expense.setPaymentMethod(request.getPaymentMethod());

        expense.setDescription(request.getDescription());

        expense.setUser(user);



        return convertToResponse(
                expenseRepository.save(expense)
        );

    }







    // ================= PAGINATION + SORTING =================


    public Page<ExpenseResponse> getMyExpenses(
            String email,
            int page,
            int size,
            String sortBy,
            String direction
    ){


        User user = getUser(email);



        Sort sort =
                direction.equalsIgnoreCase("asc")
                        ?
                        Sort.by(sortBy).ascending()
                        :
                        Sort.by(sortBy).descending();




        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        sort
                );




        return expenseRepository
                .findByUser(user,pageable)
                .map(this::convertToResponse);

    }







    // ================= GET BY ID =================


    public ExpenseResponse getExpenseById(
            Long id,
            String email
    ){


        User user=getUser(email);



        Expense expense =
                expenseRepository
                        .findByIdAndUser(id,user)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Expense not found"
                                ));



        return convertToResponse(expense);

    }







    // ================= UPDATE =================


    public ExpenseResponse updateExpense(
            Long id,
            String email,
            ExpenseRequest request
    ){


        User user=getUser(email);



        Expense expense =
                expenseRepository
                        .findByIdAndUser(id,user)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Expense not found"
                                ));




        expense.setTitle(request.getTitle());

        expense.setAmount(request.getAmount());

        expense.setCategory(request.getCategory());

        expense.setExpenseDate(request.getExpenseDate());

        expense.setPaymentMethod(request.getPaymentMethod());

        expense.setDescription(request.getDescription());



        return convertToResponse(
                expenseRepository.save(expense)
        );

    }







    // ================= DELETE =================


    public void deleteExpense(
            Long id,
            String email
    ){


        User user=getUser(email);



        Expense expense =
                expenseRepository
                        .findByIdAndUser(id,user)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Expense not found"
                                ));



        expenseRepository.delete(expense);

    }







    // ================= SEARCH =================


    public Page<ExpenseResponse> searchExpense(
            String email,
            String keyword,
            int page,
            int size
    ){


        User user=getUser(email);



        Pageable pageable =
                PageRequest.of(page,size);



        return expenseRepository
                .findByUserAndTitleContainingIgnoreCaseOrUserAndDescriptionContainingIgnoreCase(
                        user,
                        keyword,
                        user,
                        keyword,
                        pageable
                )
                .map(this::convertToResponse);

    }







    // ================= CATEGORY FILTER =================


    public Page<ExpenseResponse> getByCategory(
            String email,
            String category,
            int page,
            int size
    ){


        User user=getUser(email);



        Pageable pageable =
                PageRequest.of(page,size);



        return expenseRepository
                .findByUserAndCategory(
                        user,
                        category,
                        pageable
                )
                .map(this::convertToResponse);

    }







    // ================= GET USER =================


    private User getUser(String email){


        return userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        ));

    }







    // ================= ENTITY TO DTO =================


    private ExpenseResponse convertToResponse(
            Expense expense
    ){


        ExpenseResponse response =
                new ExpenseResponse();



        response.setId(expense.getId());

        response.setTitle(expense.getTitle());

        response.setAmount(expense.getAmount());

        response.setCategory(expense.getCategory());

        response.setExpenseDate(expense.getExpenseDate());

        response.setPaymentMethod(expense.getPaymentMethod());

        response.setDescription(expense.getDescription());

        response.setCreatedAt(expense.getCreatedAt());

        response.setUpdatedAt(expense.getUpdatedAt());



        return response;

    }


}