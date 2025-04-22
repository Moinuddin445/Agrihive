package com.DigiMarket.AgriHive.repo;

import com.DigiMarket.AgriHive.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BuyerRepo extends JpaRepository<Buyer, Long> {

    Optional<Buyer> findByFullNameAndPhoneAndPassword(String fullName , String phone, String password);


    Optional<Buyer> findByPhone(String phone);
    Optional<Buyer> findByEmail(String email);
}
