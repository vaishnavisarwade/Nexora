package com.vaishnavi.nexora.health.service;

import com.vaishnavi.nexora.entity.User;
import com.vaishnavi.nexora.exception.ResourceNotFoundException;
import com.vaishnavi.nexora.health.dto.DietRequest;
import com.vaishnavi.nexora.health.dto.DietResponse;
import com.vaishnavi.nexora.health.entity.DietRecord;
import com.vaishnavi.nexora.health.repository.DietRepository;
import com.vaishnavi.nexora.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DietService {

    @Autowired
    private DietRepository dietRepository;

    @Autowired
    private UserRepository userRepository;

    private User getLoggedInUser(Authentication authentication) {
        return userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public DietResponse createDietRecord(DietRequest request,
                                         Authentication authentication) {

        User user = getLoggedInUser(authentication);

        DietRecord record = new DietRecord();

        record.setMealType(request.getMealType());
        record.setFoodName(request.getFoodName());
        record.setQuantity(request.getQuantity());
        record.setCalories(request.getCalories());
        record.setProtein(request.getProtein());
        record.setCarbs(request.getCarbs());
        record.setFats(request.getFats());
        record.setMealTime(LocalDateTime.now());
        record.setUser(user);

        DietRecord saved = dietRepository.save(record);

        return mapToResponse(saved);
    }

    public List<DietResponse> getAllDietRecords(Authentication authentication) {

        User user = getLoggedInUser(authentication);

        return dietRepository.findByUserOrderByMealTimeDesc(user)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public DietResponse getDietRecordById(Long id,
                                          Authentication authentication) {

        User user = getLoggedInUser(authentication);

        DietRecord record = dietRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Diet record not found"));

        if (!record.getUser().getId().equals(user.getId())) {
            throw new ResourceNotFoundException("Diet record not found");
        }

        return mapToResponse(record);
    }

    public DietResponse updateDietRecord(Long id,
                                         DietRequest request,
                                         Authentication authentication) {

        User user = getLoggedInUser(authentication);

        DietRecord record = dietRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Diet record not found"));

        if (!record.getUser().getId().equals(user.getId())) {
            throw new ResourceNotFoundException("Diet record not found");
        }

        record.setMealType(request.getMealType());
        record.setFoodName(request.getFoodName());
        record.setQuantity(request.getQuantity());
        record.setCalories(request.getCalories());
        record.setProtein(request.getProtein());
        record.setCarbs(request.getCarbs());
        record.setFats(request.getFats());

        DietRecord updated = dietRepository.save(record);

        return mapToResponse(updated);
    }

    public void deleteDietRecord(Long id,
                                 Authentication authentication) {

        User user = getLoggedInUser(authentication);

        DietRecord record = dietRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Diet record not found"));

        if (!record.getUser().getId().equals(user.getId())) {
            throw new ResourceNotFoundException("Diet record not found");
        }

        dietRepository.delete(record);
    }

    private DietResponse mapToResponse(DietRecord record) {

        return new DietResponse(
                record.getId(),
                record.getMealType(),
                record.getFoodName(),
                record.getQuantity(),
                record.getCalories(),
                record.getProtein(),
                record.getCarbs(),
                record.getFats(),
                record.getMealTime()
        );
    }
}