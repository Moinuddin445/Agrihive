package com.DigiMarket.AgriHive.controller;

import com.DigiMarket.AgriHive.model.Basket;
import com.DigiMarket.AgriHive.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/basket")
public class BasketController {

    @Autowired
    private BasketService basketService;

    // Add item to basket
    @PostMapping("/add")
    public Basket addItemToBasket(@RequestParam Long userId,
                                  @RequestParam Long productId,
                                  @RequestParam(defaultValue = "1") int quantity) {
        return basketService.addItem(userId, productId, quantity);
    }

    // Get all basket items for a user
    @GetMapping("/{userId}")
    public List<Basket> getBasketItems(@PathVariable Long userId) {
        return basketService.getItemsByUserId(userId);
    }

    // Remove specific item from basket
    @DeleteMapping("/remove")
    public void removeItemFromBasket(@RequestParam Long userId,
                                     @RequestParam Long productId) {
        basketService.removeItem(userId, productId);
    }

    // Clear entire basket
    @DeleteMapping("/clear/{userId}")
    public void clearBasket(@PathVariable Long userId) {
        basketService.clearBasket(userId);
    }
}

