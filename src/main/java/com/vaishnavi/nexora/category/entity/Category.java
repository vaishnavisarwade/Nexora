package com.vaishnavi.nexora.category.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vaishnavi.nexora.expense.entity.Note;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "categories")
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(
            nullable = false,
            unique = true
    )
    private String name;





    // ================= NOTES RELATION =================


    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<Note> notes = new ArrayList<>();







    // ================= CONSTRUCTORS =================


    public Category(){

    }




    public Category(
            Long id,
            String name
    ){

        this.id = id;
        this.name = name;

    }





    public Category(
            String name
    ){

        this.name = name;

    }







    // ================= GETTERS SETTERS =================


    public Long getId(){

        return id;

    }


    public void setId(Long id){

        this.id = id;

    }





    public String getName(){

        return name;

    }


    public void setName(String name){

        this.name = name;

    }





    public List<Note> getNotes(){

        return notes;

    }


    public void setNotes(List<Note> notes){

        this.notes = notes;

    }

}