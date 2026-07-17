package com.vaishnavi.nexora.health.repository;

import com.vaishnavi.nexora.entity.User;
import com.vaishnavi.nexora.health.entity.HealthRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HealthRepository extends JpaRepository<HealthRecord, Long> {

    List<HealthRecord> findByUser(User user);

    Optional<HealthRecord> findByUserAndRecordDate(User user, LocalDate recordDate);

    List<HealthRecord> findByUserOrderByRecordDateDesc(User user);
}