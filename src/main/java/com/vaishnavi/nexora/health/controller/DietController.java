package com.vaishnavi.nexora.health.controller;

import com.vaishnavi.nexora.health.dto.DietRequest;
import com.vaishnavi.nexora.health.dto.DietResponse;
import com.vaishnavi.nexora.health.service.DietService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diet")
public class DietController {

    @Autowired
    private DietService dietService;

    @PostMapping
    public DietResponse createDietRecord(
            @Valid @RequestBody DietRequest request,
            Authentication authentication) {

        return dietService.createDietRecord(request, authentication);
    }

    @GetMapping
    public List<DietResponse> getAllDietRecords(
            Authentication authentication) {

        return dietService.getAllDietRecords(authentication);
    }

    @GetMapping("/{id}")
    public DietResponse getDietRecordById(
            @PathVariable Long id,
            Authentication authentication) {

        return dietService.getDietRecordById(id, authentication);
    }

    @PutMapping("/{id}")
    public DietResponse updateDietRecord(
            @PathVariable Long id,
            @Valid @RequestBody DietRequest request,
            Authentication authentication) {

        return dietService.updateDietRecord(id, request, authentication);
    }

    @DeleteMapping("/{id}")
    public String deleteDietRecord(
            @PathVariable Long id,
            Authentication authentication) {

        dietService.deleteDietRecord(id, authentication);
        return "Diet record deleted successfully";
    }
}