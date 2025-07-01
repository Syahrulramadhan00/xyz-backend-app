package com.xyz.xyz_backend_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.xyz.xyz_backend_app.dto.CreateProductRequestDTO;
import com.xyz.xyz_backend_app.dto.ProductDTO;
import com.xyz.xyz_backend_app.entity.Product;
import com.xyz.xyz_backend_app.exception.ResourceNotFoundException;
import com.xyz.xyz_backend_app.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProducts(String brand) {
        List<Product> products;
        if (brand != null && !brand.isEmpty()) {
            products = productRepository.findByBrandContainingIgnoreCase(brand);
        } else {
            products = productRepository.findAll();
        }
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return convertToDto(product);
    }

    @Override
    public ProductDTO createProduct(CreateProductRequestDTO requestDTO) {
        Product product = new Product();
        product.setBrand(requestDTO.getBrand());
        product.setType(requestDTO.getType());
        product.setStock(requestDTO.getStock());
        product.setPrice(requestDTO.getPrice());
        product.setDescription(requestDTO.getDescription());

        Product savedProduct = productRepository.save(product);
        return convertToDto(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, CreateProductRequestDTO productDetailsDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        product.setBrand(productDetailsDTO.getBrand());
        product.setType(productDetailsDTO.getType());
        product.setStock(productDetailsDTO.getStock());
        product.setPrice(productDetailsDTO.getPrice());
        product.setDescription(productDetailsDTO.getDescription());

        Product updatedProduct = productRepository.save(product);
        return convertToDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    private ProductDTO convertToDto(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setBrand(product.getBrand());
        dto.setType(product.getType());
        dto.setStock(product.getStock());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());
        return dto;
    }
}
