package com.DigiMarket.AgriHive.controller;

import com.DigiMarket.AgriHive.model.Farm;
import com.DigiMarket.AgriHive.model.Product;
import com.DigiMarket.AgriHive.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api")
public class FarmController {

    @Autowired
    private FarmService service;

    @GetMapping("/farms")
    public ResponseEntity<List<Farm>> getAllFarms(){
        return new ResponseEntity<>(service.getAllFarms(), HttpStatus.OK);
    }
}


