package com.ecommerce.ecommerce.entity;

import com.ecommerce.ecommerce.enums.PaymentMethod;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "payments", schema = "public")
@Data
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Positive(message = "Amount must be greater than zero")
    @Column(name = "amount", nullable = false)
    private Double amount;

    @OneToOne(mappedBy = "payment")
    private Order order;
}
