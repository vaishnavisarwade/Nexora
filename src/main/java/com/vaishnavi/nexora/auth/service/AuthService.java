package com.vaishnavi.nexora.auth.service;

import com.vaishnavi.nexora.auth.dto.RegisterRequest;

import com.vaishnavi.nexora.auth.dto.LoginRequest;
import com.vaishnavi.nexora.auth.dto.LoginResponse;
import com.vaishnavi.nexora.entity.User;
import com.vaishnavi.nexora.jwt.JwtService;
import com.vaishnavi.nexora.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private JwtService jwtService;



    public LoginResponse login(LoginRequest request) {


        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid Email or Password")
                );


        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {

            throw new RuntimeException("Invalid Email or Password");
        }


        String token = jwtService.generateToken(
                user.getEmail()
        );


        return new LoginResponse(token);
    }
    public String register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {

            throw new RuntimeException("Email already exists");

        }


        User user = new User();

        user.setName(request.getName());

        user.setEmail(request.getEmail());


        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );


        userRepository.save(user);


        return "User registered successfully";
    }
}