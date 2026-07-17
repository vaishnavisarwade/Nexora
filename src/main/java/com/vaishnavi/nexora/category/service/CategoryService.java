package com.vaishnavi.nexora.category.service;


import com.vaishnavi.nexora.category.dto.CategoryRequest;
import com.vaishnavi.nexora.category.dto.CategoryResponse;
import com.vaishnavi.nexora.category.entity.Category;
import com.vaishnavi.nexora.category.repository.CategoryRepository;
import com.vaishnavi.nexora.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class CategoryService {



    private final CategoryRepository categoryRepository;



    public CategoryService(CategoryRepository categoryRepository){

        this.categoryRepository = categoryRepository;

    }







    // ================= CREATE CATEGORY =================


    public CategoryResponse createCategory(CategoryRequest request){


        if(request.getName() == null || request.getName().trim().isEmpty()){

            throw new RuntimeException("Category name is required");

        }




        if(categoryRepository.existsByName(request.getName())){

            throw new RuntimeException("Category already exists");

        }





        Category category = new Category();


        category.setName(request.getName());



        Category savedCategory =
                categoryRepository.save(category);




        return new CategoryResponse(
                savedCategory.getId(),
                savedCategory.getName()
        );

    }







    // ================= GET ALL CATEGORIES =================


    public List<CategoryResponse> getAllCategories(){


        return categoryRepository.findAll()
                .stream()
                .map(category ->
                        new CategoryResponse(
                                category.getId(),
                                category.getName()
                        )
                )
                .toList();

    }


}