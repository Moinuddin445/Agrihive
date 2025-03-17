package com.DigiMarket.AgriHive.service;

import com.DigiMarket.AgriHive.model.User;
import com.DigiMarket.AgriHive.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo repo;

    public List<User> getUser(){
        return repo.findAll();
    }
}
