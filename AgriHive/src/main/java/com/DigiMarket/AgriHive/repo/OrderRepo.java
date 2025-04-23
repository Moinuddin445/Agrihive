package com.DigiMarket.AgriHive.repo;

import com.DigiMarket.AgriHive.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
