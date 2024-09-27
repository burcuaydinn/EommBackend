package com.ecommerce.ecommerce.dto.response;

import com.ecommerce.ecommerce.enums.PaymentMethod;

public record PaymentResponse(Long id, Long orderId, PaymentMethod paymentMethod, Double amount) {}
