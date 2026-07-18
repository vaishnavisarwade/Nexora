package com.vaishnavi.nexora.health.repository;

import com.vaishnavi.nexora.entity.User;
import com.vaishnavi.nexora.health.entity.DietRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DietRepository
        extends JpaRepository<DietRecord, Long> {

    List<DietRecord> findByUser(User user);

    List<DietRecord> findByUserOrderByMealTimeDesc(User user);

    List<DietRecord> findByUserAndMealType(
            User user,
            String mealType
    );

    List<DietRecord> findByUserAndMealTimeBetween(
            User user,
            LocalDateTime start,
            LocalDateTime end
    );

    // Used by AI endpoint
    Optional<DietRecord> findByIdAndUser(
            Long id,
            User user
    );
}