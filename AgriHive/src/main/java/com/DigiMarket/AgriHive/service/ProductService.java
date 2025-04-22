package com.DigiMarket.AgriHive.service;

import com.DigiMarket.AgriHive.model.Farm;
import com.DigiMarket.AgriHive.model.Product;
import com.DigiMarket.AgriHive.repo.FarmRepo;
import com.DigiMarket.AgriHive.repo.ProductRepo;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepo productRepo;
    private final FarmRepo farmRepo;

    @Autowired
    public ProductService(ProductRepo productRepo, FarmRepo farmRepo) {
        this.productRepo = productRepo;
        this.farmRepo = farmRepo;
    }

    // ✅ Get all products
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    // ✅ Get product by ID
    public Optional<Product> getProductById(Long id) {
        return productRepo.findById(id);
    }

    // ✅ Create product for a farm
    public Product createProduct(Long farmId, Product product) {
        Optional<Farm> farmOpt = farmRepo.findById(farmId);
        if (farmOpt.isPresent()) {
            product.setFarm(farmOpt.get()); // Directly set farm, no Optional wrapper
            return productRepo.save(product);
        } else {
            throw new RuntimeException("Farm not found with ID: " + farmId);
        }
    }

    // ✅ Update existing product
    public Product updateProduct(Long productId, Product updatedProduct) {
        return productRepo.findById(productId).map(product -> {
            product.setName(updatedProduct.getName());
            product.setCategory(updatedProduct.getCategory());
            product.setQuantity(updatedProduct.getQuantity());
            product.setPricePerKg(updatedProduct.getPricePerKg());
            product.setHarvestDate(updatedProduct.getHarvestDate());
            product.setAvailable(updatedProduct.isAvailable());
            product.setImageName(updatedProduct.getImageName());
            product.setImageType(updatedProduct.getImageType());
            product.setImageData(updatedProduct.getImageData());
            return productRepo.save(product);
        }).orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
    }

    // ✅ Delete product
    public void deleteProduct(Long id) {
        if (!productRepo.existsById(id)) {
            throw new RuntimeException("Product not found with ID: " + id);
        }
        productRepo.deleteById(id);
    }

    // ✅ Get all products for a specific farm
    public List<Product> getProductsByFarmId(Long farmId) {
        return productRepo.findByFarmFarmId(farmId);
    }

    // ✅ Add a product (with image upload support)
    public ResponseEntity<String> addProduct(String name, String category, double quantity, double pricePerKg, LocalDate harvestDate, String description, @NotNull MultipartFile imageFile) throws IOException {
        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setQuantity(quantity);
        product.setPricePerKg(pricePerKg);
        product.setHarvestDate(harvestDate);
        product.setDescription(description);
        product.setAvailable(true);

        // Handle image file
        if (imageFile != null && !imageFile.isEmpty()) {
            product.setImageName(imageFile.getOriginalFilename());
            product.setImageType(imageFile.getContentType());
            product.setImageData(imageFile.getBytes());
        }

        productRepo.save(product);

        return new ResponseEntity<>("Product added successfully", HttpStatus.CREATED); // Use CREATED status
    }
}
