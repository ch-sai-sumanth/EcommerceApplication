package com.ecommerce.project.springbootecom.repo;

import com.ecommerce.project.springbootecom.model.Category;
import com.ecommerce.project.springbootecom.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
//    Page<Product> findByCategoryOrderByProductPriceAsc(Category category, Pageable pageable);
    Page<Product> findByCategory(Category category, Pageable pageable);
    Page<Product> findByProductNameLikeIgnoreCase(String keyword,Pageable pageable);
}
