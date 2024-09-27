package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.PaymentRequest;
import com.ecommerce.ecommerce.dto.response.PaymentResponse;
import com.ecommerce.ecommerce.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @PostMapping("/process/{orderId}")
    public PaymentResponse processPayment(@PathVariable Long orderId, @RequestBody PaymentRequest paymentRequest) {
        return paymentService.processPayment(orderId, paymentRequest);
    }


    @GetMapping("/status/{orderId}")
    public PaymentResponse getPaymentByOrderId(@PathVariable Long orderId) {
        return paymentService.getPaymentByOrderId(orderId);
    }


    @DeleteMapping("/cancel/{paymentId}")
    public void cancelPayment(@PathVariable Long paymentId) {
        paymentService.cancelPayment(paymentId);
    }
}
