package com.ecommerce.ecommerce.dto.request;

import com.ecommerce.ecommerce.enums.PaymentMethod;

public record PaymentRequest(PaymentMethod paymentMethod) {}
