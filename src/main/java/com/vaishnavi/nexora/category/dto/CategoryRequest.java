package com.vaishnavi.nexora.category.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



public class CategoryRequest {



    @NotBlank(message = "Category name is required")
    @Size(
            max = 50,
            message = "Category name must be less than 50 characters"
    )
    private String name;





    // ================= CONSTRUCTORS =================


    public CategoryRequest(){

    }




    public CategoryRequest(String name){

        this.name = name;

    }





    // ================= GETTERS SETTERS =================


    public String getName(){

        return name;

    }


    public void setName(String name){

        this.name = name;

    }


}