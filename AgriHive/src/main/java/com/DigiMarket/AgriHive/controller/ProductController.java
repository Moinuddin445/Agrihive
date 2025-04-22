package com.DigiMarket.AgriHive.controller;

import com.DigiMarket.AgriHive.model.Product;
import com.DigiMarket.AgriHive.service.FarmService;
import com.DigiMarket.AgriHive.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/products")
@CrossOrigin("*") // Optional: allow frontend access
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private FarmService farmService;

    // ✅ Get all products
    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // ✅ Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Create a new product for a specific farm
    @PostMapping("/farm/{farmId}")
    public ResponseEntity<Product> createProduct(@PathVariable Long farmId, @RequestBody Product product) {
        try {
            Product createdProduct = productService.createProduct(farmId, product);
            return ResponseEntity.ok(createdProduct);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    // ✅ Update a product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        try {
            Product updated = productService.updateProduct(id, updatedProduct);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Get products by farmId
    @GetMapping("/farm/{farmId}")
    public List<Product> getProductsByFarm(@PathVariable Long farmId) {
        return productService.getProductsByFarmId(farmId);
    }
}
