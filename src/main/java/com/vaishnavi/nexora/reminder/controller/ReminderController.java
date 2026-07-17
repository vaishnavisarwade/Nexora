package com.vaishnavi.nexora.reminder.controller;


import com.vaishnavi.nexora.reminder.dto.ReminderRequest;
import com.vaishnavi.nexora.reminder.dto.ReminderResponse;
import com.vaishnavi.nexora.reminder.service.ReminderService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
@RequestMapping("/reminders")
public class ReminderController {



    @Autowired
    private ReminderService reminderService;





    // CREATE REMINDER

    @PostMapping
    public ResponseEntity<ReminderResponse> createReminder(
            @Valid @RequestBody ReminderRequest request,
            Authentication authentication
    ){

        String email = authentication.getName();


        return new ResponseEntity<>(
                reminderService.createReminder(request, email),
                HttpStatus.CREATED
        );

    }





    // GET USER REMINDERS

    @GetMapping
    public ResponseEntity<List<ReminderResponse>> getReminders(
            Authentication authentication
    ){

        String email = authentication.getName();


        return ResponseEntity.ok(
                reminderService.getUserReminders(email)
        );

    }





    // UPDATE REMINDER

    @PutMapping("/{id}")
    public ResponseEntity<ReminderResponse> updateReminder(
            @PathVariable Long id,
            @Valid @RequestBody ReminderRequest request,
            Authentication authentication
    ){

        String email = authentication.getName();


        return ResponseEntity.ok(
                reminderService.updateReminder(
                        id,
                        request,
                        email
                )
        );

    }





    // MARK COMPLETED

    @PutMapping("/{id}/complete")
    public ResponseEntity<ReminderResponse> completeReminder(
            @PathVariable Long id,
            Authentication authentication
    ){

        String email = authentication.getName();


        return ResponseEntity.ok(
                reminderService.completeReminder(
                        id,
                        email
                )
        );

    }





    // DELETE REMINDER

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReminder(
            @PathVariable Long id,
            Authentication authentication
    ){

        String email = authentication.getName();


        reminderService.deleteReminder(
                id,
                email
        );


        return ResponseEntity.ok(
                "Reminder deleted successfully"
        );

    }


}