//package com.DigiMarket.AgriHive.DTO;
//
//import com.DigiMarket.AgriHive.model.Product;
//
//public class ProductDTO {
//    private String productId;
//    private String name;
//    private String category;
//    private String description;
//    private double pricePerKg;
//    private int quantity;
//    private String imageName;
//    private String farmName;
//    private String location;
//    private String farmerName;
//
//    public ProductDTO(Product product) {
//        this.productId = String.valueOf(product.getProductId());
//        this.name = product.getName();
//        this.category = product.getCategory();
//        this.description = product.getDescription();
//        this.pricePerKg = product.getPricePerKg();
//        this.quantity = (int) product.getQuantity();
//        this.imageName = product.getImageName();
//
//        // Handle Optional<Farm> and its fields
//        product.getFarm().ifPresent(farm -> {
//            this.farmName = farm.getFarmName();
//            this.location = farm.getLocation();
//
//            // Handle Optional<Farmer> inside the farm
//            farm.getFarmer().ifPresent(farmer -> {
//                this.farmerName = farmer.getName();
//            });
//        });
//    }
//
//    // Getters and setters for DTO fields (if necessary)
//}
//
