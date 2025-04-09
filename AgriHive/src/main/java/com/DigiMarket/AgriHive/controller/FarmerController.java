package com.DigiMarket.AgriHive.controller;

import com.DigiMarket.AgriHive.model.Farmer;
import com.DigiMarket.AgriHive.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class FarmerController {

    @Autowired
    private FarmerService service;

    @GetMapping("/farmers")
    public ResponseEntity<List<Farmer>> getAllFarmers(){
        return new ResponseEntity<>(service.getAllFarmers(), HttpStatus.OK);
    }
}
