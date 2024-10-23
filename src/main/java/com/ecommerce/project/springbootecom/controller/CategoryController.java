package com.ecommerce.project.springbootecom.controller;

import com.ecommerce.project.springbootecom.AppConstants;
import com.ecommerce.project.springbootecom.model.Category;
import com.ecommerce.project.springbootecom.payload.CategoryDTO;
import com.ecommerce.project.springbootecom.payload.CategoryResponse;
import com.ecommerce.project.springbootecom.service.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class CategoryController {

    @Autowired
    CategoryServiceImpl categoryServiceImpl;

    @GetMapping("categories")
    public ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam(name="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) int pageNumber ,
            @RequestParam(name="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) int pageSize,
            @RequestParam(name="sortBy",defaultValue= AppConstants.SORT_BY,required=false) String sortBy,
            @RequestParam(name="orderBy",defaultValue= AppConstants.SORT_ORDER,required=false) String order)
    {
        CategoryResponse categoryResponse=categoryServiceImpl.findAllCategories(pageNumber,pageSize,sortBy,order);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @GetMapping("categories/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long categoryId) {
        CategoryDTO categoryDTO= categoryServiceImpl.findCategoryById(categoryId);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @PostMapping("categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO newCategoryDTO= categoryServiceImpl.addCategory(categoryDTO);
        return new ResponseEntity<>(newCategoryDTO, HttpStatus.CREATED);
    }

    @PutMapping("categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO updatedCategoryDTO= categoryServiceImpl.updateCategory(categoryId,categoryDTO);
        return new ResponseEntity<>(updatedCategoryDTO, HttpStatus.OK);
    }

    @DeleteMapping("categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId) {
        CategoryDTO deletedCategoryDto= categoryServiceImpl.deleteCategory(categoryId);
        return new ResponseEntity<>(deletedCategoryDto, HttpStatus.OK);
    }


}
