package com.DigiMarket.AgriHive.repo;

import com.DigiMarket.AgriHive.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasketRepo extends JpaRepository<Basket, Long> {

    Optional<Basket> findByUserIdAndProductId(Long userId, Long productId);

    List<Basket> findAllByUserId(Long userId);

    void deleteByUserIdAndProductId(Long userId, Long productId);

    void deleteAllByUserId(Long userId);
}

