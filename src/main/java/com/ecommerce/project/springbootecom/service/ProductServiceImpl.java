package com.ecommerce.project.springbootecom.service;

import com.ecommerce.project.springbootecom.exception.ResourceNotFoundException;
import com.ecommerce.project.springbootecom.model.Category;
import com.ecommerce.project.springbootecom.model.Product;
import com.ecommerce.project.springbootecom.payload.CategoryDTO;
import com.ecommerce.project.springbootecom.payload.ProductDTO;
import com.ecommerce.project.springbootecom.payload.ProductResponse;
import com.ecommerce.project.springbootecom.repo.CategoryRepo;
import com.ecommerce.project.springbootecom.repo.ProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepo productRepo;
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    FileServiceImpl fileService;

    @Value("${project.image}")
    private String path;

    @Override
    public ProductDTO createProduct(Long categoryId,ProductDTO productDTO) {

        Category category=categoryRepo.findById(categoryId).orElseThrow(()->
                                                new ResourceNotFoundException("Category","CategoryId",categoryId));
        Product product=modelMapper.map(productDTO, Product.class);
        product.setCategory(category);
        product.setProductImage("default.png");
        Double specialPrice=product.getProductPrice()-(product.getProductPrice()*product.getDiscount()*0.01);
        product.setSpecialPrice(specialPrice);

        Product savedProduct=productRepo.save(product);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public ProductResponse getAllProducts() {

        List<Product> allProducts=productRepo.findAll();
        ProductResponse productResponse=new ProductResponse();

        List<ProductDTO> productDTOS=allProducts.stream()
                .map(product -> modelMapper.map(product,ProductDTO.class)).toList();
        productResponse.setContent(productDTOS);
        return productResponse;
    }

    @Override
    public ProductResponse getProductsByCategory(Long categoryId) {
        Category category=categoryRepo.findById(categoryId)
                        .orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
        List<Product> productListByCategory=productRepo.findByCategoryOrderByProductPriceAsc(category);

        List<ProductDTO> allProductsListByCategoryDTOs= productListByCategory.stream()
                .map(product -> modelMapper.map(product,ProductDTO.class)).toList();

        ProductResponse productResponse=new ProductResponse();
        productResponse.setContent(allProductsListByCategoryDTOs);
        return productResponse;
    }

    @Override
    public ProductResponse getProductsByKeyword(String keyword) {
        //%Keyword%
        List<Product> productListByCategory=productRepo.findByProductNameLikeIgnoreCase('%'+keyword+'%');

        List<ProductDTO> allProductsListByCategoryDTOs= productListByCategory.stream()
                .map(product -> modelMapper.map(product,ProductDTO.class)).toList();

        ProductResponse productResponse=new ProductResponse();
        productResponse.setContent(allProductsListByCategoryDTOs);
        return productResponse;
    }

    @Override
    public ProductDTO updateProduct(Long productId,ProductDTO productDTO) {
        Product existingProduct=productRepo.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product","ProductId",productId));

        Product product=modelMapper.map(productDTO,Product.class);
        existingProduct.setProductName(product.getProductName());
        existingProduct.setProductDescription(product.getProductDescription());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setDiscount(product.getDiscount());
        existingProduct.setProductPrice(product.getProductPrice());
        existingProduct.setSpecialPrice(product.getSpecialPrice());

        productRepo.save(existingProduct);
        return modelMapper.map(existingProduct,ProductDTO.class);
    }

    @Override
    public ProductDTO deleteProduct(Long productId) {
        Product savedProduct=productRepo.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product","productId",productId));
        productRepo.delete(savedProduct);
        return modelMapper.map(savedProduct,ProductDTO.class);
    }

    @Override
    public ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException {
        //Get the Product
        Product product=productRepo.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product","productId",productId));
        //uploading image to server
        //get the file name of the image
        String fileName=fileService.uploadImage(path,image);

        //update the new file name to the Product
        product.setProductImage(fileName);
        productRepo.save(product);
        //return DTO after mapping product to DTO
        return modelMapper.map(product,ProductDTO.class);
    }


}
