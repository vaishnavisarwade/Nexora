package com.vaishnavi.nexora.controller;


import com.vaishnavi.nexora.dto.UserRequest;
import com.vaishnavi.nexora.dto.UserResponse;
import com.vaishnavi.nexora.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping
    public UserResponse createUser(
            @Valid @RequestBody UserRequest request
    ){

        return userService.saveUser(request);

    }


    @GetMapping
    public List<UserResponse> getAllUsers(){

        return userService.getAllUsers();

    }


    @GetMapping("/{id}")
    public UserResponse getUserById(
            @PathVariable Long id
    ){

        return userService.getUserById(id);

    }


    @PutMapping("/{id}")
    public UserResponse updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequest request
    ){

        return userService.updateUser(id, request);

    }


    @DeleteMapping("/{id}")
    public String deleteUser(
            @PathVariable Long id
    ){

        userService.deleteUser(id);

        return "User deleted successfully";

    }


    // JWT TEST API
    @GetMapping("/test")
    public String testAuthentication(
            Authentication authentication
    ){

        return "Logged in as: " + authentication.getName();

    }


    // Get logged-in user profile
    @GetMapping("/me")
    public UserResponse getCurrentUser(
            Authentication authentication
    ){

        String email = authentication.getName();

        return userService.getUserByEmail(email);

    }
    @PutMapping("/me")
    public UserResponse updateMyProfile(
            Authentication authentication,
            @RequestBody UserRequest request
    ){

        String email = authentication.getName();

        return userService.updateUserByEmail(email, request);

    }


}