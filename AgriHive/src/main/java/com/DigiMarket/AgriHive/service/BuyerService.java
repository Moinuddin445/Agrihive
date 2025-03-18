package com.DigiMarket.AgriHive.service;

import com.DigiMarket.AgriHive.model.Buyer;
import com.DigiMarket.AgriHive.repo.BuyerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerService {

    @Autowired
    BuyerRepo repo;

    public List<Buyer> getBuyer(){

        return repo.findAll();
    }
}
