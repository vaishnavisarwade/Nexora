package com.vaishnavi.nexora.service;


import com.vaishnavi.nexora.dto.UserRequest;
import com.vaishnavi.nexora.dto.UserResponse;
import com.vaishnavi.nexora.entity.User;
import com.vaishnavi.nexora.exception.ResourceNotFoundException;
import com.vaishnavi.nexora.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;



    public UserResponse saveUser(UserRequest request){

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        User savedUser = userRepository.save(user);

        return convertToResponse(savedUser);
    }



    public List<UserResponse> getAllUsers(){

        return userRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

    }



    public UserResponse getUserById(Long id){

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );

        return convertToResponse(user);

    }



    public UserResponse updateUser(Long id, UserRequest request){

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );


        user.setName(request.getName());
        user.setEmail(request.getEmail());


        if(request.getPassword() != null &&
                !request.getPassword().isEmpty()){

            user.setPassword(
                    passwordEncoder.encode(request.getPassword())
            );

        }


        User updatedUser = userRepository.save(user);


        return convertToResponse(updatedUser);

    }



    public void deleteUser(Long id){

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );

        userRepository.delete(user);

    }



    public UserResponse getUserByEmail(String email){

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );


        return convertToResponse(user);

    }




    public UserResponse updateUserByEmail(
            String email,
            UserRequest request
    ){

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );


        user.setName(request.getName());


        if(request.getEmail() != null &&
                !request.getEmail().isEmpty()){

            user.setEmail(request.getEmail());

        }


        if(request.getPassword() != null &&
                !request.getPassword().isEmpty()){

            user.setPassword(
                    passwordEncoder.encode(request.getPassword())
            );

        }


        User updatedUser = userRepository.save(user);


        return convertToResponse(updatedUser);

    }




    private UserResponse convertToResponse(User user){

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );

    }

}