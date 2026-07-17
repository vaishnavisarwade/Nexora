package com.vaishnavi.nexora.health.controller;

import com.vaishnavi.nexora.health.dto.HealthRequest;
import com.vaishnavi.nexora.health.dto.HealthResponse;
import com.vaishnavi.nexora.health.service.HealthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/health")
public class HealthController {

    @Autowired
    private HealthService healthService;

    @PostMapping
    public HealthResponse createHealthRecord(
            @Valid @RequestBody HealthRequest request,
            Authentication authentication) {

        return healthService.createHealthRecord(request, authentication);
    }

    @GetMapping
    public List<HealthResponse> getAllHealthRecords(
            Authentication authentication) {

        return healthService.getAllHealthRecords(authentication);
    }

    @GetMapping("/{id}")
    public HealthResponse getHealthRecordById(
            @PathVariable Long id,
            Authentication authentication) {

        return healthService.getHealthRecordById(id, authentication);
    }

    @PutMapping("/{id}")
    public HealthResponse updateHealthRecord(
            @PathVariable Long id,
            @Valid @RequestBody HealthRequest request,
            Authentication authentication) {

        return healthService.updateHealthRecord(id, request, authentication);
    }

    @DeleteMapping("/{id}")
    public String deleteHealthRecord(
            @PathVariable Long id,
            Authentication authentication) {

        healthService.deleteHealthRecord(id, authentication);
        return "Health record deleted successfully";
    }
}