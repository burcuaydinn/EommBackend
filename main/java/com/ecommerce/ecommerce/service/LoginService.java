package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.LoginRequest;
import com.ecommerce.ecommerce.dto.response.LoginResponse;

public interface LoginService {


    LoginResponse loginUser(LoginRequest request);


    void logoutUser(Long userId);
}
