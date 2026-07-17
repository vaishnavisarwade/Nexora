package com.vaishnavi.nexora.dto;

import com.vaishnavi.nexora.category.dto.CategoryResponse;
public class NoteResponse {


    private Long id;


    private String title;


    private String content;


    private String color;


    private CategoryResponse category;





    // ================= CONSTRUCTORS =================


    public NoteResponse() {

    }





    public NoteResponse(
            Long id,
            String title,
            String content,
            String color,
            CategoryResponse category
    ){

        this.id = id;
        this.title = title;
        this.content = content;
        this.color = color;
        this.category = category;

    }





    // ================= GETTERS SETTERS =================


    public Long getId() {

        return id;

    }


    public void setId(Long id) {

        this.id = id;

    }





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





    public String getColor() {

        return color;

    }


    public void setColor(String color) {

        this.color = color;

    }





    public CategoryResponse getCategory() {

        return category;

    }


    public void setCategory(CategoryResponse category) {

        this.category = category;

    }

}