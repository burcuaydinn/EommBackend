package com.ecommerce.ecommerce.dto.response;

public record LoginResponse(Long userId, String username, String email,String password, String role) {}
