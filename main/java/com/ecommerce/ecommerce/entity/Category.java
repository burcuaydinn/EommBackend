package com.ecommerce.ecommerce.entity;

import com.ecommerce.ecommerce.enums.CategoryType;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "categories", schema = "public")
@Data
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Category name is required")
    @Column(name = "name", nullable = false)
    private String name;  // Kategori adı (örneğin, Elektronik, Moda vb.)

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Category type is required")
    @Column(name = "type", nullable = false)
    private CategoryType type;  // CategoryType enum'ı (örneğin, ELECTRONICS, FASHION)
}
