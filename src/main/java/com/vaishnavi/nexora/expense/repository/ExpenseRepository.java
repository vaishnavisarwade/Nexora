package com.vaishnavi.nexora.expense.repository;


import com.vaishnavi.nexora.entity.User;
import com.vaishnavi.nexora.expense.entity.Expense;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.util.Optional;



public interface ExpenseRepository extends JpaRepository<Expense, Long> {



    // ================= PAGINATION =================

    Page<Expense> findByUser(
            User user,
            Pageable pageable
    );





    // ================= FIND SINGLE USER EXPENSE =================

    Optional<Expense> findByIdAndUser(
            Long id,
            User user
    );






    // ================= SEARCH TITLE + DESCRIPTION =================

    Page<Expense> findByUserAndTitleContainingIgnoreCaseOrUserAndDescriptionContainingIgnoreCase(
            User user1,
            String titleKeyword,
            User user2,
            String descriptionKeyword,
            Pageable pageable
    );






    // ================= FILTER CATEGORY =================

    Page<Expense> findByUserAndCategory(
            User user,
            String category,
            Pageable pageable
    );







    // ================= DATE RANGE FILTER =================

    Page<Expense> findByUserAndExpenseDateBetween(
            User user,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    );







    // ================= DASHBOARD =================



    @Query(
            "SELECT COALESCE(SUM(e.amount),0) " +
                    "FROM Expense e " +
                    "WHERE e.user = :user"
    )
    Double getTotalExpense(
            @Param("user") User user
    );







    @Query(
            "SELECT COALESCE(SUM(e.amount),0) " +
                    "FROM Expense e " +
                    "WHERE e.user = :user " +
                    "AND e.expenseDate BETWEEN :startDate AND :endDate"
    )
    Double getMonthlyExpense(
            @Param("user") User user,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );


}