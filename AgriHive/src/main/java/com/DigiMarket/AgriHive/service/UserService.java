package com.DigiMarket.AgriHive.service;

import com.DigiMarket.AgriHive.model.Buyer;
import com.DigiMarket.AgriHive.model.Farmer;
import com.DigiMarket.AgriHive.repo.BuyerRepo;
import com.DigiMarket.AgriHive.repo.FarmerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private BuyerRepo buyerRepository;

    @Autowired
    private FarmerRepo farmerRepository;

    public String loginUser(String name, String phone, String password) {
        // Check if it's a Buyer
        Optional<Buyer> buyer = buyerRepository.findByFullNameAndPhoneAndPassword(name , phone, password);
        if (buyer.isPresent()) {
            return "Login successful";
        }

        // Check if it's a Farmer
        Optional<Farmer> farmer = farmerRepository.findByNameAndPhoneAndPassword(name , phone, password);
        if (farmer.isPresent()) {
            return "Login successful";
        }

        return "Invalid credentials!";
    }
}
