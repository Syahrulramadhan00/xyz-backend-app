package com.xyz.xyz_backend_app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.xyz.xyz_backend_app.dto.CreateProductRequestDTO;
import com.xyz.xyz_backend_app.dto.ProductDTO;
import com.xyz.xyz_backend_app.service.ProductService;

@RestController
@RequestMapping("/api/products") 
@CrossOrigin(origins = "*") 
public class ProductController {

    @Autowired
    private ProductService productService;

    // GET /api/products or /api/products?brand=toyota
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(@RequestParam(required = false) String brand) {
        List<ProductDTO> products = productService.getAllProducts(brand);
        return ResponseEntity.ok(products);
    }

    // GET /api/products/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // POST /api/products
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody CreateProductRequestDTO createProductRequestDTO) {
        ProductDTO createdProduct = productService.createProduct(createProductRequestDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    // PUT /api/products/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody CreateProductRequestDTO productDetailsDTO) {
        ProductDTO updatedProduct = productService.updateProduct(id, productDetailsDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    // DELETE /api/products/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
