package com.DigiMarket.AgriHive.controller;

import com.DigiMarket.AgriHive.model.Buyer;
import com.DigiMarket.AgriHive.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/buyers")
@CrossOrigin("*")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    // Create new buyer (Signup)
    @PostMapping("/signup")
    public ResponseEntity<Buyer> createBuyer(@RequestBody Buyer buyer) {
        Buyer createdBuyer = buyerService.createBuyer(buyer);
        return ResponseEntity.ok(createdBuyer);
    }

    // Login using email & password
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Buyer loginRequest) {
        Optional<Buyer> buyerOpt = buyerService.getBuyerByEmail(loginRequest.getEmail());
        if (buyerOpt.isPresent()) {
            Buyer buyer = buyerOpt.get();
            if (buyer.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok(buyer);
            } else {
                return ResponseEntity.status(401).body("Invalid password");
            }
        } else {
            return ResponseEntity.status(404).body("Buyer not found");
        }
    }

    // Get all buyers
    @GetMapping
    public ResponseEntity<List<Buyer>> getAllBuyers() {
        return ResponseEntity.ok(buyerService.getAllBuyers());
    }

    // Get buyer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Buyer> getBuyerById(@PathVariable Long id) {
        return buyerService.getBuyerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update buyer
    @PutMapping("/{id}")
    public ResponseEntity<Buyer> updateBuyer(@PathVariable Long id, @RequestBody Buyer updatedBuyer) {
        Buyer result = buyerService.updateBuyer(id, updatedBuyer);
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete buyer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuyer(@PathVariable Long id) {
        buyerService.deleteBuyer(id);
        return ResponseEntity.noContent().build();
    }
}
