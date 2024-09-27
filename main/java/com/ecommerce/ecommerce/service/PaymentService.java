package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.PaymentRequest;
import com.ecommerce.ecommerce.dto.response.PaymentResponse;

public interface PaymentService {


    PaymentResponse processPayment(Long orderId, PaymentRequest request);


    PaymentResponse getPaymentByOrderId(Long orderId);


    void cancelPayment(Long paymentId);
}
