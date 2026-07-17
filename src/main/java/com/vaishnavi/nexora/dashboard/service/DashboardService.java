package com.vaishnavi.nexora.dashboard.service;


import com.vaishnavi.nexora.dashboard.dto.DashboardResponse;
import com.vaishnavi.nexora.entity.User;
import com.vaishnavi.nexora.expense.repository.ExpenseRepository;
import com.vaishnavi.nexora.repository.NoteRepository;
import com.vaishnavi.nexora.repository.UserRepository;
import com.vaishnavi.nexora.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import java.time.LocalDate;



@Service
public class DashboardService {



    private final NoteRepository noteRepository;

    private final ExpenseRepository expenseRepository;

    private final UserRepository userRepository;





    public DashboardService(

            NoteRepository noteRepository,

            ExpenseRepository expenseRepository,

            UserRepository userRepository

    ){

        this.noteRepository = noteRepository;

        this.expenseRepository = expenseRepository;

        this.userRepository = userRepository;

    }








    // ================= DASHBOARD SUMMARY =================


    public DashboardResponse getDashboardSummary(String email){



        User user = userRepository.findByEmail(email)

                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );





        long totalNotes =
                noteRepository.countByUser(user);




        long favoriteNotes =
                noteRepository.countByUserAndFavoriteTrue(user);






        Double totalExpenses =
                expenseRepository.getTotalExpense(user);





        LocalDate startDate =
                LocalDate.now()
                        .withDayOfMonth(1);



        LocalDate endDate =
                LocalDate.now()
                        .withDayOfMonth(
                                LocalDate.now().lengthOfMonth()
                        );





        Double monthlyExpense =
                expenseRepository.getMonthlyExpense(
                        user,
                        startDate,
                        endDate
                );






        return new DashboardResponse(

                totalNotes,

                favoriteNotes,

                totalExpenses,

                monthlyExpense

        );

    }


}