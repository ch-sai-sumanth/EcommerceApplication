package com.ecommerce.project.springbootecom.controller;

import com.ecommerce.project.springbootecom.model.Category;
import com.ecommerce.project.springbootecom.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        return categoryService.findAllCategories();
    }

    @GetMapping("categories/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
        return categoryService.findCategoryById(categoryId);
    }

    @PostMapping("categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @PutMapping("categories/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
        return categoryService.updateCategory(categoryId,category);
    }

    @DeleteMapping("categories/{categoryId}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long categoryId) {
        return categoryService.deleteCategory(categoryId);
    }


}
