package com.xyz.xyz_backend_app.service;

import java.util.List;

import com.xyz.xyz_backend_app.dto.CreateProductRequestDTO;
import com.xyz.xyz_backend_app.dto.ProductDTO;

public interface ProductService {
    List<ProductDTO> getAllProducts(String brand);
    ProductDTO getProductById(Long id);
    ProductDTO createProduct(CreateProductRequestDTO requestDTO);
    ProductDTO updateProduct(Long id, CreateProductRequestDTO PruductDetailsDTO);
    void deleteProduct(Long id);    
}

