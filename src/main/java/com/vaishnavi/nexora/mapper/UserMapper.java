package com.vaishnavi.nexora.mapper;

import com.vaishnavi.nexora.dto.UserRequest;
import com.vaishnavi.nexora.dto.UserResponse;
import com.vaishnavi.nexora.entity.User;

public class UserMapper {


    public static User toEntity(UserRequest request){

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        return user;
    }


    public static UserResponse toResponse(User user){

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());

        return response;
    }
}