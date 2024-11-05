package com.ecommerce.project.springbootecom.service;

import com.ecommerce.project.springbootecom.model.Category;
import com.ecommerce.project.springbootecom.model.Product;
import com.ecommerce.project.springbootecom.payload.ProductDTO;
import com.ecommerce.project.springbootecom.payload.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface ProductService {
    public ProductDTO createProduct(Long categoryId, ProductDTO productDTO);
    public ProductResponse getAllProducts(Integer pageNumber,Integer pageSize,String sortBy,String sortOrder);
    ProductResponse getProductsByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    ProductDTO updateProduct(Long productId, ProductDTO productDTO);
    ProductDTO deleteProduct(Long productId);
    ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException;
    ProductResponse getProductsByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
}
