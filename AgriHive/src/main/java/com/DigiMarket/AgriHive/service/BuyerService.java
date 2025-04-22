package com.DigiMarket.AgriHive.service;

import com.DigiMarket.AgriHive.model.Buyer;
import com.DigiMarket.AgriHive.repo.BuyerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuyerService {

    @Autowired
    private BuyerRepo buyerRepo;

    // Save a new buyer
    public Buyer createBuyer(Buyer buyer) {
        return buyerRepo.save(buyer);
    }

    // Get all buyers
    public List<Buyer> getAllBuyers() {
        return buyerRepo.findAll();
    }

    // Get buyer by ID
    public Optional<Buyer> getBuyerById(Long id) {
        return buyerRepo.findById(id);
    }

    // Get buyer by phone
    public Optional<Buyer> getBuyerByPhone(String phone) {
        return buyerRepo.findByPhone(phone);
    }

    // Get buyer by email
    public Optional<Buyer> getBuyerByEmail(String email) {
        return buyerRepo.findByEmail(email);
    }

    // Update buyer
    public Buyer updateBuyer(Long id, Buyer updatedBuyer) {
        return buyerRepo.findById(id).map(buyer -> {
            buyer.setfullName(updatedBuyer.getfullName());
            buyer.setPhone(updatedBuyer.getPhone());
            buyer.setEmail(updatedBuyer.getEmail());
            buyer.setPassword(updatedBuyer.getPassword());
            buyer.setShippingAddress(updatedBuyer.getShippingAddress());
            return buyerRepo.save(buyer);
        }).orElse(null);
    }

    // Delete buyer
    public void deleteBuyer(Long id) {
        buyerRepo.deleteById(id);
    }
}
