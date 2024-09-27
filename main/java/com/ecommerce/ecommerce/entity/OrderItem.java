package com.ecommerce.ecommerce.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "order_items", schema = "public")
@Data
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than zero")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    @Column(name = "price", nullable = false)
    private Double price;  // Ürün fiyatı

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // Toplam fiyatı hesaplayan method (fiyat * miktar)
    public Double getTotalPrice() {
        return price * quantity;
    }
}
