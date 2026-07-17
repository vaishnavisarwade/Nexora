package com.vaishnavi.nexora.category.controller;


import com.vaishnavi.nexora.category.dto.CategoryRequest;
import com.vaishnavi.nexora.category.dto.CategoryResponse;
import com.vaishnavi.nexora.category.service.CategoryService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/categories")
@PreAuthorize("isAuthenticated()")
public class CategoryController {



    private final CategoryService categoryService;



    public CategoryController(CategoryService categoryService){

        this.categoryService = categoryService;

    }







    // ================= CREATE CATEGORY =================


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse createCategory(

            @Valid @RequestBody CategoryRequest request

    ){

        return categoryService.createCategory(request);

    }







    // ================= GET ALL CATEGORIES =================


    @GetMapping
    public List<CategoryResponse> getAllCategories(){

        return categoryService.getAllCategories();

    }


}