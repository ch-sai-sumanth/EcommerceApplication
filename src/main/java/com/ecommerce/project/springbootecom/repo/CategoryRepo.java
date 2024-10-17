package com.ecommerce.project.springbootecom.repo;

import com.ecommerce.project.springbootecom.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
}
