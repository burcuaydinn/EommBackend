package com.ecommerce.ecommerce.entity;

import com.ecommerce.ecommerce.enums.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders", schema = "public")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull(message = "Order date is required")
    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Order status is required")
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;

    // Siparişin toplam fiyatını hesaplayan method
    public Double getTotalPrice() {
        return orderItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())  // OrderItem içindeki fiyat ve miktar üzerinden hesaplama
                .sum();
    }
}
