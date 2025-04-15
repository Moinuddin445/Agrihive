package com.DigiMarket.AgriHive.service;

import com.DigiMarket.AgriHive.model.Farm;
import com.DigiMarket.AgriHive.model.Farmer;
import com.DigiMarket.AgriHive.repo.FarmRepo;
import com.DigiMarket.AgriHive.repo.FarmerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmService {

    @Autowired
    private FarmRepo farmRepository;

    @Autowired
    private FarmerRepo farmerRepository;

    // ✅ Get all farms
    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }

    // ✅ Get a farm by its ID
    public Optional<Farm> getFarmById(Long id) {
        return farmRepository.findById(id);
    }

    // ✅ Create a farm under a specific farmer
    public Farm createFarm(Long farmerId, Farm farm) {
        Optional<Farmer> farmerOpt = farmerRepository.findById(farmerId);
        if (farmerOpt.isPresent()) {
            Farmer farmer = farmerOpt.get();
            farm.setFarmer(farmer);
            return farmRepository.save(farm);
        } else {
            throw new RuntimeException("Farmer not found with ID: " + farmerId);
        }
    }

    // ✅ Update farm details
    public Farm updateFarm(Long id, Farm updatedFarm) {
        return farmRepository.findById(id).map(farm -> {
            farm.setFarmName(updatedFarm.getFarmName());
            farm.setLocation(updatedFarm.getLocation());
            return farmRepository.save(farm);
        }).orElseThrow(() -> new RuntimeException("Farm not found with ID: " + id));
    }

    // ✅ Delete a farm
    public void deleteFarm(Long id) {
        if (!farmRepository.existsById(id)) {
            throw new RuntimeException("Farm not found with ID: " + id);
        }
        farmRepository.deleteById(id);
    }

    // ✅ Get all farms of a specific farmer
    public List<Farm> getFarmsByFarmerId(Long farmerId) {
        return farmRepository.findByFarmerFarmerId(farmerId);
    }
}
