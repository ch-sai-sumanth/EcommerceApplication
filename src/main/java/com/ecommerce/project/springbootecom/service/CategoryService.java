package com.ecommerce.project.springbootecom.service;

import com.ecommerce.project.springbootecom.model.Category;
import com.ecommerce.project.springbootecom.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    public ResponseEntity<List<Category>> findAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        if(categories.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    public ResponseEntity<Category> findCategoryById(Long id) {
        Optional<Category> category = categoryRepo.findById(id);

        Category savedCategory = category.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found"));

        return new ResponseEntity<>(category.get(), HttpStatus.OK);
    }

    public ResponseEntity<Category> addCategory(Category category) {
        categoryRepo.save(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    public Category updateCategory(Long categoryId, Category category) {
        Optional<Category> categoryOptional = categoryRepo.findById(categoryId);
        Category savedCategory = categoryOptional.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found"));

        savedCategory.setCategoryName(category.getCategoryName());
        categoryRepo.save(savedCategory);
        return savedCategory;
    }

    public String deleteCategory(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepo.findById(categoryId);
        Category savedCategory = categoryOptional.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found"));

        categoryRepo.deleteById(categoryId);
        return "Category with catrgoryId "+categoryId+" deleted successfully";
    }
}
