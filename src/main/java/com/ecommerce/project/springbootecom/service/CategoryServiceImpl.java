package com.ecommerce.project.springbootecom.service;

import com.ecommerce.project.springbootecom.exception.APIException;
import com.ecommerce.project.springbootecom.exception.ResourceNotFoundException;
import com.ecommerce.project.springbootecom.model.Category;
import com.ecommerce.project.springbootecom.payload.CategoryDTO;
import com.ecommerce.project.springbootecom.payload.CategoryResponse;
import com.ecommerce.project.springbootecom.repo.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    ModelMapper modelMapper;

    public CategoryResponse findAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        if(categories.isEmpty())
            throw  new APIException("No Category created till now");


        List<CategoryDTO> categoryDTOS=categories.stream()
                .map(category -> modelMapper.map(category,CategoryDTO.class)).toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);

        return categoryResponse;
    }

    public CategoryDTO findCategoryById(Long categoryId) {
        Category savedCategory = categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));

        return modelMapper.map(savedCategory,CategoryDTO.class);
    }

    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO,Category.class);
        Category categoryFromDb = categoryRepo.findByCategoryName(category.getCategoryName());
        if(categoryFromDb!= null)
            throw new APIException("Category with CategoryName "+category.getCategoryName()+" already exists!!!");

        Category savedCategory=categoryRepo.save(category);
        return modelMapper.map(savedCategory,CategoryDTO.class);
    }

    public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO) {

        Category savedCategory = categoryRepo.findById(categoryId)
                                                .orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));

        Category category = modelMapper.map(categoryDTO,Category.class);
        category.setCategoryId(categoryId);
        savedCategory=categoryRepo.save(category);
        return modelMapper.map(savedCategory,CategoryDTO.class);
    }

    public CategoryDTO deleteCategory(Long categoryId) {
        Category savedCategory = categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));

        categoryRepo.deleteById(categoryId);
        return modelMapper.map(savedCategory,CategoryDTO.class);
    }
}
