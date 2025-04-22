package com.DigiMarket.AgriHive.DTO;

public class ProductDTO {
    private Long productId;
    private String name;
    private String description;
    private String category;
    private double pricePerKg;
    private double quantity;
    private String farmName;
    private String location;
    private String farmerName;
    private String imageName;

    // Constructor
    public ProductDTO(Long productId, String name, String description, String category,
                      double pricePerKg, int quantity, String farmName, String location,
                      String farmerName, String imageName) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.category = category;
        this.pricePerKg = pricePerKg;
        this.quantity = quantity;
        this.farmName = farmName;
        this.location = location;
        this.farmerName = farmerName;
        this.imageName = imageName;
    }

    // Getters and setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPricePerKg() {
        return pricePerKg;
    }

    public void setPricePerKg(double pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }
}
