package com.ecommerce.project.springbootecom.service;

import com.ecommerce.project.springbootecom.model.Category;
import com.ecommerce.project.springbootecom.model.Product;
import com.ecommerce.project.springbootecom.payload.ProductDTO;
import com.ecommerce.project.springbootecom.payload.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface ProductService {
    public ProductDTO createProduct(Long categoryId, ProductDTO productDTO);
    public ProductResponse getAllProducts();

    public ProductResponse getProductsByCategory(Long categoryId);

    ProductResponse getProductsByKeyword(String keyword);

    ProductDTO updateProduct(Long productId, ProductDTO productDTO);

    ProductDTO deleteProduct(Long productId);

    ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException;
}
