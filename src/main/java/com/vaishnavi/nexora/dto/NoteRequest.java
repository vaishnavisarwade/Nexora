package com.vaishnavi.nexora.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class NoteRequest {


    @NotBlank(message = "Title is required")
    private String title;



    @NotBlank(message = "Content is required")
    private String content;



    // Category selected by user
    private Long categoryId;



    // Note color
    @Size(max = 50, message = "Color name is too long")
    private String color = "WHITE";





    // ================= CONSTRUCTORS =================


    public NoteRequest() {

    }




    public NoteRequest(
            String title,
            String content,
            Long categoryId,
            String color
    ){

        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
        this.color = color;

    }





    // ================= GETTERS SETTERS =================


    public String getTitle() {

        return title;

    }


    public void setTitle(String title) {

        this.title = title;

    }





    public String getContent() {

        return content;

    }


    public void setContent(String content) {

        this.content = content;

    }





    public Long getCategoryId() {

        return categoryId;

    }


    public void setCategoryId(Long categoryId) {

        this.categoryId = categoryId;

    }





    public String getColor() {

        return color;

    }


    public void setColor(String color) {

        this.color = color;

    }

}