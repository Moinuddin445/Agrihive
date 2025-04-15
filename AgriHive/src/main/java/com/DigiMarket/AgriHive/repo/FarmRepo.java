package com.DigiMarket.AgriHive.repo;

import com.DigiMarket.AgriHive.model.Farm;
import com.DigiMarket.AgriHive.model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmRepo extends JpaRepository<Farm, Long> {
    List<Farm> findByFarmer(Farmer farmer);

    List<Farm> findByFarmerFarmerId(Long farmerId);// Fetch farms by farmer
}

