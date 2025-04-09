package com.DigiMarket.AgriHive.service;

import com.DigiMarket.AgriHive.model.Farmer;
import com.DigiMarket.AgriHive.repo.FarmerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmerService {

    @Autowired
    private FarmerRepo repo;

    public List<Farmer> getAllFarmers(){

        return repo.findAll();
    }
}
