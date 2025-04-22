package com.DigiMarket.AgriHive.controller;

import com.DigiMarket.AgriHive.model.Farmer;
import com.DigiMarket.AgriHive.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/farmers")
@CrossOrigin(origins = "*")
public class FarmerController {

    @Autowired
    private FarmerService service;

    // ✅ Signup
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Farmer farmer) {
        if (service.existsByPhone(farmer.getPhone())) {
            return ResponseEntity.badRequest().body("Phone number already registered.");
        }
        if (service.existsByEmail(farmer.getEmail())) {
            return ResponseEntity.badRequest().body("Email already registered.");
        }
        Farmer saved = service.saveFarmer(farmer);
        return ResponseEntity.ok(saved);
    }


    // ✅ Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Farmer loginRequest) {
        Optional<Farmer> farmerOpt = service.findByEmail(loginRequest.getEmail());
        if (farmerOpt.isPresent()) {
            Farmer farmer = farmerOpt.get();
            if (farmer.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok(farmer);
            } else {
                return ResponseEntity.status(401).body("Invalid password");
            }
        } else {
            return ResponseEntity.status(404).body("Farmer not found");
        }
    }

    // 🔁 Get all
    @GetMapping
    public List<Farmer> getAllFarmers() {
        return service.getAllFarmers();
    }

    // 🔁 Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<Farmer> getFarmerById(@PathVariable Long id) {
        Optional<Farmer> farmer = service.getFarmerById(id);
        return farmer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔁 Update
    @PutMapping("/{id}")
    public ResponseEntity<Farmer> updateFarmer(@PathVariable Long id, @RequestBody Farmer updatedFarmer) {
        try {
            Farmer farmer = service.updateFarmer(id, updatedFarmer);
            return ResponseEntity.ok(farmer);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔁 Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarmer(@PathVariable Long id) {
        service.deleteFarmer(id);
        return ResponseEntity.noContent().build();
    }

    // 🔁 Get by Phone
    @GetMapping("/phone/{phone}")
    public ResponseEntity<Farmer> getFarmerByPhone(@PathVariable String phone) {
        Optional<Farmer> farmer = service.findByPhone(phone);
        return farmer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔁 Get by Email
    @GetMapping("/email/{email}")
    public ResponseEntity<Farmer> getFarmerByEmail(@PathVariable String email) {
        Optional<Farmer> farmer = service.findByEmail(email);
        return farmer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
