package com.ecommerce.ecommerce.dto.response;

import com.ecommerce.ecommerce.enums.RoleType;

public record UserResponse(Long id, String username, String email,String password, RoleType role) {}
