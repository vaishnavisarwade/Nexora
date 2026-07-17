package com.vaishnavi.nexora.calendar.repository;


import com.vaishnavi.nexora.calendar.entity.Event;
import com.vaishnavi.nexora.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.Optional;



@Repository
public interface EventRepository extends JpaRepository<Event, Long> {




    // ================= PAGINATION =================


    Page<Event> findByUser(
            User user,
            Pageable pageable
    );







    // ================= FIND SINGLE USER EVENT =================


    Optional<Event> findByIdAndUser(
            Long id,
            User user
    );







    // ================= SEARCH TITLE =================


    Page<Event> findByUserAndTitleContainingIgnoreCase(
            User user,
            String keyword,
            Pageable pageable
    );







    // ================= SEARCH TITLE + DESCRIPTION =================


    Page<Event> findByUserAndTitleContainingIgnoreCaseOrUserAndDescriptionContainingIgnoreCase(
            User user1,
            String titleKeyword,
            User user2,
            String descriptionKeyword,
            Pageable pageable
    );







    // ================= EVENTS BETWEEN DATES =================


    Page<Event> findByUserAndEventDateBetween(
            User user,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable
    );







    // ================= UPCOMING EVENTS =================


    Page<Event> findByUserAndEventDateAfter(
            User user,
            LocalDateTime date,
            Pageable pageable
    );







    // ================= COMPLETED FILTER =================


    Page<Event> findByUserAndCompleted(
            User user,
            boolean completed,
            Pageable pageable
    );


}