package com.ecommerce.project.springbootecom.service;

import com.ecommerce.project.springbootecom.model.Category;
import com.ecommerce.project.springbootecom.payload.CategoryDTO;
import com.ecommerce.project.springbootecom.payload.CategoryResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    public CategoryResponse findAllCategories();
    public CategoryDTO findCategoryById(Long categoryId);
    public CategoryDTO addCategory(CategoryDTO categoryDTO);
    public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO);
    public CategoryDTO deleteCategory(Long categoryId);

    }
