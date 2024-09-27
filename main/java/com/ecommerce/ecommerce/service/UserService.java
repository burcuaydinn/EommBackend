package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.response.UserResponse;

import java.util.List;

public interface UserService {


    UserResponse getUserById(Long userId);


    UserResponse updateUser(Long userId, UserResponse userResponse);


    void deleteUser(Long userId);


    UserResponse addUser(UserResponse userResponse);


    List<UserResponse> getAllUsers();
}
