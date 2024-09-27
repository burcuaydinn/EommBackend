package com.ecommerce.ecommerce.entity;

import com.ecommerce.ecommerce.enums.CategoryType;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

@Entity
@Table(name = "products", schema = "public")
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Product name is required")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Description is required")
    @Column(name = "description")
    private String description;

    @Positive(message = "Price must be greater than zero")
    @Column(name = "price", nullable = false)
    private Double price;

    @Positive(message = "Stock quantity must be greater than zero")
    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Category is required")
    @Column(name = "category", nullable = false)
    private CategoryType category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

   // @ManyToOne
   // @JoinColumn(name = "user_id")
//private User user;
}
