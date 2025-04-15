package com.DigiMarket.AgriHive.controller;

import com.DigiMarket.AgriHive.model.Farm;
import com.DigiMarket.AgriHive.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/farms")
@CrossOrigin(origins = "*") // CORS for frontend usage
public class FarmController {

    @Autowired
    private FarmService farmService;

    // GET all farms
    @GetMapping
    public List<Farm> getAllFarms() {
        return farmService.getAllFarms();
    }

    // GET farm by ID
    @GetMapping("/{id}")
    public ResponseEntity<Farm> getFarmById(@PathVariable Long id) {
        Optional<Farm> farm = farmService.getFarmById(id);
        return farm.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET all farms by Farmer ID
    @GetMapping("/farmer/{farmerId}")
    public List<Farm> getFarmsByFarmer(@PathVariable Long farmerId) {
        return farmService.getFarmsByFarmerId(farmerId);
    }

    // POST: Create a new farm under a specific farmer
    @PostMapping("/farmer/{farmerId}")
    public ResponseEntity<Farm> createFarm(@PathVariable Long farmerId, @RequestBody Farm farm) {
        try {
            Farm savedFarm = farmService.createFarm(farmerId, farm);
            return ResponseEntity.ok(savedFarm);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT: Update farm by ID
    @PutMapping("/{id}")
    public ResponseEntity<Farm> updateFarm(@PathVariable Long id, @RequestBody Farm updatedFarm) {
        try {
            Farm farm = farmService.updateFarm(id, updatedFarm);
            return ResponseEntity.ok(farm);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Delete farm by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarm(@PathVariable Long id) {
        farmService.deleteFarm(id);
        return ResponseEntity.noContent().build();
    }
}
