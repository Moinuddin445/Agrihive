package com.DigiMarket.AgriHive.repo;

import com.DigiMarket.AgriHive.model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmerRepo extends JpaRepository<Farmer, Long> {

}
