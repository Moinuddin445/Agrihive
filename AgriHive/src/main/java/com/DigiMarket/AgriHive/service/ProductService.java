package com.DigiMarket.AgriHive.service;

import com.DigiMarket.AgriHive.model.Product;
import com.DigiMarket.AgriHive.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepo repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return repo.findById(id);
    }

    public Product addProduct(Product product) {
    }
}
