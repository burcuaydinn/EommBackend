package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.PaymentRequest;
import com.ecommerce.ecommerce.dto.response.PaymentResponse;
import com.ecommerce.ecommerce.entity.Order;
import com.ecommerce.ecommerce.entity.Payment;
import com.ecommerce.ecommerce.enums.PaymentMethod;
import com.ecommerce.ecommerce.repository.OrderRepository;
import com.ecommerce.ecommerce.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public PaymentResponse processPayment(Long orderId, PaymentRequest request) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));


        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentMethod(request.paymentMethod());
        payment.setAmount(order.getTotalPrice());  // Sipariş toplam fiyatı üzerinden işlem yapılır

        paymentRepository.save(payment);

        return new PaymentResponse(payment.getId(), payment.getOrder().getId(), payment.getPaymentMethod(), payment.getAmount());
    }

    @Override
    public PaymentResponse getPaymentByOrderId(Long orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        return new PaymentResponse(payment.getId(), payment.getOrder().getId(), payment.getPaymentMethod(), payment.getAmount());
    }

    @Override
    public void cancelPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        paymentRepository.delete(payment);
    }
}
