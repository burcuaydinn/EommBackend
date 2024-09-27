package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.PaymentRequest;
import com.ecommerce.ecommerce.dto.response.PaymentResponse;
import com.ecommerce.ecommerce.enums.PaymentMethod;
import com.ecommerce.ecommerce.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PaymentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController paymentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(paymentController).build();
    }

    @Test
    void testProcessPayment() throws Exception {
        PaymentResponse paymentResponse = new PaymentResponse(1L, 1L, PaymentMethod.CREDIT_CARD, 100.0);

        when(paymentService.processPayment(eq(1L), any(PaymentRequest.class))).thenReturn(paymentResponse);

        mockMvc.perform(post("/api/payment/process/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"paymentMethod\": \"CREDIT_CARD\", \"amount\": 100.0 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.paymentMethod").value("CREDIT_CARD"))
                .andExpect(jsonPath("$.amount").value(100.0));
    }

    @Test
    void testGetPaymentByOrderId() throws Exception {
        PaymentResponse paymentResponse = new PaymentResponse(1L, 1L, PaymentMethod.CASH, 50.0);

        when(paymentService.getPaymentByOrderId(1L)).thenReturn(paymentResponse);

        mockMvc.perform(get("/api/payment/status/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.paymentMethod").value("CASH"))
                .andExpect(jsonPath("$.amount").value(50.0));
    }

    @Test
    void testCancelPayment() throws Exception {
        doNothing().when(paymentService).cancelPayment(1L);

        mockMvc.perform(delete("/api/payment/cancel/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}