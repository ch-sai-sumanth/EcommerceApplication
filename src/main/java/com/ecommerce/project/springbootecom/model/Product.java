package com.ecommerce.project.springbootecom.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="products")
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @NotBlank
    @Size(min = 3, message = "ProductName must have atleast 3 characters")
    private String productName;
    private String productImage;
    @NotBlank
    @Size(min = 6, message = "ProductDescription  must have atleast 6 characters")
    private String productDescription;
    private Integer quantity;
    private Double productPrice;
    private Double discount;
    private Double specialPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne()
    @JoinColumn(name="seller_id")
    private User user;
}
