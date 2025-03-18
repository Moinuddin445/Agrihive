package com.DigiMarket.AgriHive.controller;

import com.DigiMarket.AgriHive.model.Buyer;
import com.DigiMarket.AgriHive.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    BuyerService service;


    @GetMapping("/buyer")
    public List<Buyer> getBuyer(){

        return service.getBuyer();
    }
}
