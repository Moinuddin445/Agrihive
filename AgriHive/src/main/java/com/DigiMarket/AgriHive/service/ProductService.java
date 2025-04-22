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
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public ResponseEntity<String> addProduct(Long farmId, String name, String category, double quantity,
                                             double pricePerKg, LocalDate harvestDate, String description,
                                             MultipartFile imageFile) throws IOException {
        try {
            String uploadDir = "/Users/khajamoinuddin/Downloads/RFPbackend/AgriHive/src/main/resources/static/Pimages";
            // Check if the image is present and handle the image file
            String imageName = null;
            String imageType = null;
            byte[] imageData = null;

            if (imageFile != null && !imageFile.isEmpty()) {
                // Generate a unique file name to avoid name conflicts
                String originalFilename = StringUtils.cleanPath(imageFile.getOriginalFilename());
                imageName = originalFilename;
                imageType = imageFile.getContentType();

                // Convert the image file to byte[] for database storage
                imageData = imageFile.getBytes();

                // Save the file to disk (optional step, if you want to store the image physically)
                Path imagePath = Paths.get(uploadDir, imageName);
                Files.copy(imageFile.getInputStream(), imagePath);
            }

            // Retrieve the farm from the database
            Farm farm = farmRepo.findById(farmId).orElseThrow(() -> new RuntimeException("Farm not found"));

            // Create a new Product object and set its properties
            Product product = new Product();
            product.setFarm(farm);
            product.setName(name);
            product.setCategory(category);
            product.setQuantity(quantity);
            product.setPricePerKg(pricePerKg);
            product.setHarvestDate(harvestDate);
            product.setDescription(description);
            product.setImageName(imageName);
            product.setImageType(imageType);
            product.setImageData(imageData);

            // Save the product to the database
            productRepo.save(product);

            // Return a success message
            return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully!");

        } catch (IOException e) {
            // If there's an error during file processing or saving the product
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing the image or saving product: " + e.getMessage());
        } catch (Exception e) {
            // General error handling
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding product: " + e.getMessage());
        }
    }


    // Method to save the image to a specific directory
    private String saveImage(MultipartFile imageFile) throws IOException {
        // Define the upload directory (use relative path for platform-independent directory)
        Path uploadDir = Paths.get("uploads");

        // Create the directory if it doesn't exist
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        // Get the original file name
        String fileName = imageFile.getOriginalFilename();

        // Define the path where the image will be saved
        Path targetPath = uploadDir.resolve(fileName);

        // Save the image file to the target directory
        imageFile.transferTo(targetPath.toFile());

        // Return the file path where the image was saved
        return targetPath.toString(); // You can store this path in the product if needed
    }
    public Product saveProduct(Product product) {
        return productRepo.save(product);
    }

}
