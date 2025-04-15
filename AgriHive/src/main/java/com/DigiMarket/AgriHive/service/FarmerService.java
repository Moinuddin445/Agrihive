package com.DigiMarket.AgriHive.service;

import com.DigiMarket.AgriHive.model.Farmer;
import com.DigiMarket.AgriHive.repo.FarmerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmerService {

    @Autowired
    private FarmerRepo repo;

    // Get all farmers
    public List<Farmer> getAllFarmers() {
        return repo.findAll();
    }

    // Get farmer by ID
    public Optional<Farmer> getFarmerById(Long id) {
        return repo.findById(id);
    }

    // Create or update farmer
    public Farmer saveFarmer(Farmer farmer) {
        return repo.save(farmer);
    }

    // Update existing farmer
    public Farmer updateFarmer(Long id, Farmer updatedFarmer) {
        return repo.findById(id).map(farmer -> {
            farmer.setName(updatedFarmer.getName());
            farmer.setPhone(updatedFarmer.getPhone());
            farmer.setEmail(updatedFarmer.getEmail());
            farmer.setPassword(updatedFarmer.getPassword());
            farmer.setAddress(updatedFarmer.getAddress());
            return repo.save(farmer);
        }).orElseThrow(() -> new RuntimeException("Farmer not found with ID: " + id));
    }

    // Delete farmer by ID
    public void deleteFarmer(Long id) {
        repo.deleteById(id);
    }

    // Check if a farmer exists by phone
    public boolean existsByPhone(String phone) {
        return repo.existsByPhone(phone);
    }

    // Check if a farmer exists by email
    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }

    // Find farmer by phone
    public Optional<Farmer> findByPhone(String phone) {
        return repo.findByPhone(phone);
    }

    // Find farmer by email
    public Optional<Farmer> findByEmail(String email) {
        return repo.findByEmail(email);
    }
}
