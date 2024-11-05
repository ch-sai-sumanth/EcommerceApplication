package com.ecommerce.project.springbootecom.repo;

import com.ecommerce.project.springbootecom.model.Category;
import com.ecommerce.project.springbootecom.model.Product;
import com.ecommerce.project.springbootecom.payload.ProductResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByCategoryOrderByProductPriceAsc(Category category);
    List<Product> findByProductNameLikeIgnoreCase(String keyword);
}
