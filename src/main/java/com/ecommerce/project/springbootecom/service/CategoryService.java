package com.ecommerce.project.springbootecom.service;

import com.ecommerce.project.springbootecom.model.Category;
import com.ecommerce.project.springbootecom.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        if(!category.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(category.get(), HttpStatus.OK);
    }

    public ResponseEntity<Category> addCategory(Category category) {
        categoryRepo.save(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    public ResponseEntity<Category> updateCategory(Long categoryId, Category category) {
        Optional<Category> categoryOptional = categoryRepo.findById(categoryId);
        if(!categoryOptional.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Category categoryUpdated = categoryOptional.get();
        categoryUpdated.setCategoryName(category.getCategoryName());
        categoryRepo.save(categoryUpdated);
        return new ResponseEntity<>(categoryUpdated, HttpStatus.OK);
    }

    public ResponseEntity<Category> deleteCategory(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepo.findById(categoryId);
        if(!categoryOptional.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        categoryRepo.deleteById(categoryId);
        return new ResponseEntity<>(categoryOptional.get(), HttpStatus.OK);
    }
}
