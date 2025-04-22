package com.DigiMarket.AgriHive.controller;

import com.DigiMarket.AgriHive.DTO.ProductDTO;
import com.DigiMarket.AgriHive.model.Product;
import com.DigiMarket.AgriHive.service.FarmService;
import com.DigiMarket.AgriHive.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO productDTO = productService.getProductById(id).getBody();

        // Check if the productDTO is not null
        if (productDTO != null) {
            return ResponseEntity.ok(productDTO);  // Return 200 OK with the productDTO
        } else {
            return ResponseEntity.notFound().build();  // Return 404 Not Found if the product doesn't exist
        }
    }


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

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addProduct(
            @RequestParam("farmId") Long farmId,
            @RequestParam("name") String name,
            @RequestParam("category") String category,
            @RequestParam("quantity") double quantity,
            @RequestParam("pricePerKg") double pricePerKg,
            @RequestParam("harvestDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate harvestDate,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile imageFile
    ) {
        try {
            // Call service layer to handle the product creation
            String responseMessage = String.valueOf(productService.addProduct(farmId, name, category, quantity, pricePerKg, harvestDate, description, imageFile));

            // Create a response map to return as JSON
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", responseMessage);

            return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
        } catch (Exception e) {
            // In case of error, return JSON with error message
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
