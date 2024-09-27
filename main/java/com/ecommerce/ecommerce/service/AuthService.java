package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.RegisterRequest;
import com.ecommerce.ecommerce.dto.response.RegisterResponse;

public interface AuthService {


    RegisterResponse registerUser(RegisterRequest request);


    void assignRole(Long userId, String role);


    void changePassword(Long userId, String newPassword);


    void resetPassword(String email);
}
