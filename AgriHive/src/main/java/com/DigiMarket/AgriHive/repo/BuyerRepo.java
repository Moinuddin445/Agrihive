package com.DigiMarket.AgriHive.repo;

import com.DigiMarket.AgriHive.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepo extends JpaRepository<Buyer, Long> {
    //Optional<User> fin(String phone);
}
