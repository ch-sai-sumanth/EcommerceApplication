package com.ecommerce.project.springbootecom.service;

import com.ecommerce.project.springbootecom.exception.APIException;
import com.ecommerce.project.springbootecom.exception.ResourceNotFoundException;
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
            throw  new APIException("No Category created till now");

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    public ResponseEntity<Category> findCategoryById(Long categoryId) {
        Optional<Category> category = categoryRepo.findById(categoryId);

        Category savedCategory = category.orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));

        return new ResponseEntity<>(category.get(), HttpStatus.OK);
    }

    public ResponseEntity<Category> addCategory(Category category) {

        Category savedCategory = categoryRepo.findByCategoryName(category.getCategoryName());
        if(savedCategory!= null)
            throw new APIException("Category with CategoryName "+category.getCategoryName()+" already exists!!!");
        categoryRepo.save(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    public Category updateCategory(Long categoryId, Category category) {

        Category existingCategory = categoryRepo.findByCategoryName(category.getCategoryName());
        if(existingCategory!= null)
            throw new APIException("Category with CategoryName "+category.getCategoryName()+" already exists!!!");

        Optional<Category> categoryOptional = categoryRepo.findById(categoryId);
        Category savedCategory = categoryOptional.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found"));

        savedCategory.setCategoryName(category.getCategoryName());
        categoryRepo.save(savedCategory);
        return savedCategory;
    }

    public String deleteCategory(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepo.findById(categoryId);
        Category savedCategory = categoryOptional.orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));

        categoryRepo.deleteById(categoryId);
        return "Category with catrgoryId "+categoryId+" deleted successfully";
    }
}
