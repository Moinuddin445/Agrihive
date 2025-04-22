package com.DigiMarket.AgriHive.service;

import com.DigiMarket.AgriHive.model.Basket;
import com.DigiMarket.AgriHive.repo.BasketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BasketService {

    @Autowired
    private BasketRepo basketRepository;

    // Add item to basket (if item exists for the user, update quantity)
    public Basket addItem(Long userId, Long productId, int quantity) {
        Optional<Basket> existingItem = basketRepository.findByUserIdAndProductId(userId, productId);

        if (existingItem.isPresent()) {
            Basket basket = existingItem.get();
            basket.setQuantity(basket.getQuantity() + quantity);
            return basketRepository.save(basket);
        } else {
            Basket newItem = new Basket(userId, productId, quantity);
            return basketRepository.save(newItem);
        }
    }

    // Get all basket items for a user
    public List<Basket> getItemsByUserId(Long userId) {
        return basketRepository.findAllByUserId(userId);
    }

    // Remove a specific item from basket
    public void removeItem(Long userId, Long productId) {
        basketRepository.deleteByUserIdAndProductId(userId, productId);
    }

    // Clear basket for a user
    public void clearBasket(Long userId) {
        basketRepository.deleteAllByUserId(userId);
    }
}

