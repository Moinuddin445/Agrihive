package com.DigiMarket.AgriHive.service;

import com.DigiMarket.AgriHive.model.Farm;
import com.DigiMarket.AgriHive.model.Product;
import com.DigiMarket.AgriHive.repo.FarmRepo;
import com.DigiMarket.AgriHive.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private FarmRepo farmRepo;

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
            product.setFarm(farmOpt.get());
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
}
