package com.DigiMarket.AgriHive.repo;

import com.DigiMarket.AgriHive.model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FarmerRepo extends JpaRepository<Farmer, Long> {

        Optional<Farmer> findByNameAndPhoneAndPassword(String name , String phone, String password);

    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
    Optional<Farmer> findByPhone(String phone);
    Optional<Farmer> findByEmail(String email);
}
