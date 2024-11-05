package com.ecommerce.project.springbootecom.payload;

import com.ecommerce.project.springbootecom.model.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long productId;
    private String productName;
    private String productImage;
    private String productDescription;
    private Integer quantity;
    private Double productPrice;
    private Double discount;
    private Double specialPrice;

    @ManyToOne
    private Category category;
}
