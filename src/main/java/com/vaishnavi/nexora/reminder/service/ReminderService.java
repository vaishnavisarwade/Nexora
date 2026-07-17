package com.vaishnavi.nexora.reminder.service;


import com.vaishnavi.nexora.entity.User;
import com.vaishnavi.nexora.exception.ResourceNotFoundException;
import com.vaishnavi.nexora.reminder.dto.ReminderRequest;
import com.vaishnavi.nexora.reminder.dto.ReminderResponse;
import com.vaishnavi.nexora.reminder.entity.Reminder;
import com.vaishnavi.nexora.reminder.repository.ReminderRepository;
import com.vaishnavi.nexora.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class ReminderService {


    @Autowired
    private ReminderRepository reminderRepository;


    @Autowired
    private UserRepository userRepository;



    // CREATE REMINDER

    public ReminderResponse createReminder(
            ReminderRequest request,
            String email
    ) {


        User user = userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User not found")
                );


        Reminder reminder = new Reminder();

        reminder.setTitle(request.getTitle());
        reminder.setDescription(request.getDescription());
        reminder.setReminderDate(request.getReminderDate());
        reminder.setUser(user);


        Reminder savedReminder =
                reminderRepository.save(reminder);


        return mapToResponse(savedReminder);
    }





    // GET ALL USER REMINDERS

    public List<ReminderResponse> getUserReminders(String email) {


        User user = userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User not found")
                );


        return reminderRepository
                .findByUserId(user.getId())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

    }





    // UPDATE REMINDER

    public ReminderResponse updateReminder(
            Long id,
            ReminderRequest request,
            String email
    ) {


        Reminder reminder =
                reminderRepository.findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Reminder not found")
                        );


        if(!reminder.getUser().getEmail().equals(email)){
            throw new RuntimeException("Unauthorized access");
        }


        reminder.setTitle(request.getTitle());
        reminder.setDescription(request.getDescription());
        reminder.setReminderDate(request.getReminderDate());


        Reminder updated =
                reminderRepository.save(reminder);


        return mapToResponse(updated);

    }





    // MARK AS COMPLETED

    public ReminderResponse completeReminder(
            Long id,
            String email
    ) {


        Reminder reminder =
                reminderRepository.findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Reminder not found")
                        );


        if(!reminder.getUser().getEmail().equals(email)){
            throw new RuntimeException("Unauthorized access");
        }


        reminder.setCompleted(true);


        return mapToResponse(
                reminderRepository.save(reminder)
        );

    }





    // DELETE REMINDER

    public void deleteReminder(
            Long id,
            String email
    ) {


        Reminder reminder =
                reminderRepository.findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Reminder not found")
                        );


        if(!reminder.getUser().getEmail().equals(email)){
            throw new RuntimeException("Unauthorized access");
        }


        reminderRepository.delete(reminder);

    }





    // ENTITY TO DTO CONVERSION

    private ReminderResponse mapToResponse(
            Reminder reminder
    ){

        return new ReminderResponse(
                reminder.getId(),
                reminder.getTitle(),
                reminder.getDescription(),
                reminder.getReminderDate(),
                reminder.isCompleted(),
                reminder.getCreatedAt()
        );

    }

}