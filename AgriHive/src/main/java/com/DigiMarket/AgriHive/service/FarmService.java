package com.DigiMarket.AgriHive.service;

import com.DigiMarket.AgriHive.model.Farm;
import com.DigiMarket.AgriHive.model.Farmer;
import com.DigiMarket.AgriHive.repo.FarmRepo;
import com.DigiMarket.AgriHive.repo.FarmerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FarmService {
    @Autowired
    private FarmRepo farmRepository;
//    @Autowired
//    private FarmerRepo farmerRepository;

    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }
}

