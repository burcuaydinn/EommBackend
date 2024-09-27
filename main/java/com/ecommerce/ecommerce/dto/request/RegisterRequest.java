package com.ecommerce.ecommerce.dto.request;

import com.ecommerce.ecommerce.enums.RoleType;

public record RegisterRequest(String username, String email, String password, RoleType role) {}
