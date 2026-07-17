package com.vaishnavi.nexora.health.service;

import com.vaishnavi.nexora.entity.User;
import com.vaishnavi.nexora.exception.ResourceNotFoundException;
import com.vaishnavi.nexora.health.dto.HealthRequest;
import com.vaishnavi.nexora.health.dto.HealthResponse;
import com.vaishnavi.nexora.health.entity.HealthRecord;
import com.vaishnavi.nexora.health.repository.HealthRepository;
import com.vaishnavi.nexora.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HealthService {

    @Autowired
    private HealthRepository healthRepository;

    @Autowired
    private UserRepository userRepository;

    private User getLoggedInUser(Authentication authentication) {
        return userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private Double calculateBMI(Double weight, Double height) {
        if (weight == null || height == null || height <= 0) {
            return 0.0;
        }
        return Math.round((weight / (height * height)) * 100.0) / 100.0;
    }

    public HealthResponse createHealthRecord(HealthRequest request,
                                             Authentication authentication) {

        User user = getLoggedInUser(authentication);

        HealthRecord record = new HealthRecord();

        record.setWeight(request.getWeight());
        record.setHeight(request.getHeight());
        record.setBmi(calculateBMI(request.getWeight(), request.getHeight()));
        record.setWaterIntake(request.getWaterIntake());
        record.setSleepHours(request.getSleepHours());
        record.setSteps(request.getSteps());
        record.setExerciseMinutes(request.getExerciseMinutes());
        record.setRecordDate(LocalDate.now());
        record.setUser(user);

        return mapToResponse(healthRepository.save(record));
    }

    public List<HealthResponse> getAllHealthRecords(Authentication authentication) {

        User user = getLoggedInUser(authentication);

        return healthRepository.findByUserOrderByRecordDateDesc(user)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public HealthResponse getHealthRecordById(Long id,
                                              Authentication authentication) {

        User user = getLoggedInUser(authentication);

        HealthRecord record = healthRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Health record not found"));

        if (!record.getUser().getId().equals(user.getId())) {
            throw new ResourceNotFoundException("Health record not found");
        }

        return mapToResponse(record);
    }

    public HealthResponse updateHealthRecord(Long id,
                                             HealthRequest request,
                                             Authentication authentication) {

        User user = getLoggedInUser(authentication);

        HealthRecord record = healthRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Health record not found"));

        if (!record.getUser().getId().equals(user.getId())) {
            throw new ResourceNotFoundException("Health record not found");
        }

        record.setWeight(request.getWeight());
        record.setHeight(request.getHeight());
        record.setBmi(calculateBMI(request.getWeight(), request.getHeight()));
        record.setWaterIntake(request.getWaterIntake());
        record.setSleepHours(request.getSleepHours());
        record.setSteps(request.getSteps());
        record.setExerciseMinutes(request.getExerciseMinutes());

        return mapToResponse(healthRepository.save(record));
    }

    public void deleteHealthRecord(Long id,
                                   Authentication authentication) {

        User user = getLoggedInUser(authentication);

        HealthRecord record = healthRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Health record not found"));

        if (!record.getUser().getId().equals(user.getId())) {
            throw new ResourceNotFoundException("Health record not found");
        }

        healthRepository.delete(record);
    }

    private HealthResponse mapToResponse(HealthRecord record) {

        return new HealthResponse(
                record.getId(),
                record.getWeight(),
                record.getHeight(),
                record.getBmi(),
                record.getWaterIntake(),
                record.getSleepHours(),
                record.getSteps(),
                record.getExerciseMinutes(),
                record.getRecordDate()
        );
    }
}